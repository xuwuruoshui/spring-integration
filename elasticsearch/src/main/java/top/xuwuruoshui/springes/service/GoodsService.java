package top.xuwuruoshui.springes.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import top.xuwuruoshui.springes.pojo.Goods;
import top.xuwuruoshui.springes.utils.HtmlParseUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    public GoodsService(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public Boolean parseGoods(String keywords) throws IOException {
        List<Goods> goodsList = new HtmlParseUtil().parseJD(keywords);

        List<IndexQuery> indexquery = goodsList.stream().map(goods -> {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setObject(goods);
            return indexQuery;
        }).collect(Collectors.toList());


        List<String> list = elasticsearchRestTemplate.bulkIndex(indexquery, IndexCoordinates.of("jd"));

        return list.size()>0;
    }

    public List<Goods> searchGoods(String keyword, int page, int size) throws IOException {


        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withPageable(PageRequest.of(page,size));


        //布尔查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        //标题关键字精确匹配,还可以加入boost权重，数值越大权重越大，比如想主要看title有没有keyword就可以吧boost设置的比较高
        //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", keyword);

        boolQuery.should(matchQueryBuilder);

        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("title")
                .requireFieldMatch(false)
                .preTags("<span style='color:red'>")
                .postTags("</span>");

        NativeSearchQuery nativeSearchQuery = queryBuilder
                .withQuery(boolQuery)
                .withHighlightBuilder(highlightBuilder)
                .build();

        SearchHits<Goods> hits = elasticsearchRestTemplate.search(nativeSearchQuery, Goods.class, IndexCoordinates.of("jd"));

        return hits.stream().map(hit->{
            List<String> titleList = hit.getHighlightField("title");
            titleList.forEach(title->{
                hit.getContent().setTitle(title);

            });
            return hit.getContent();
        }).collect(Collectors.toList());

        //return hits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
