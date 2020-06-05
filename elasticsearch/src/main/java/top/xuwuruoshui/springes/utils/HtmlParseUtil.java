package top.xuwuruoshui.springes.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.xuwuruoshui.springes.pojo.Goods;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {

    public List<Goods> parseJD(String keyword) throws IOException {


        String url = "https://search.jd.com/Search?keyword="+keyword+"&enc=utf-8";

        //解析网页,得到的结果和js就很像了
        Document document = Jsoup.parse(new URL(url),30000);
        Element element = document.getElementById("J_goodsList");

        //获取所有的li元素
        Elements list = element.getElementsByTag("li");

        List<Goods> goodslist = new ArrayList<>();
        list.forEach(li->{
            //src可能还未加载，可以用懒加载source-data-lazy-img
            String img = li.getElementsByTag("img").eq(0).attr("src");
            String price = li.getElementsByClass("p-price").eq(0).text();
            String title = li.getElementsByClass("p-name").eq(0).text();
            Goods goods = new Goods().builder().img(img).price(price).title(title).build();
            goodslist.add(goods);
        });
        return goodslist;
    }

    public static void main(String[] args) {
        try {
            List<Goods> goods = new HtmlParseUtil().parseJD(args[0]);
            goods.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
