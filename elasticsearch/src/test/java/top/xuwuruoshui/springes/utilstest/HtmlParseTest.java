package top.xuwuruoshui.springes.utilstest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URL;

@SpringBootTest
public class HtmlParseTest {
    @Test
    public void test() throws IOException {
        String url = "https://search.jd.com/Search?keyword=java";

        //解析网页,得到的结果和js就很像了
        Document document = Jsoup.parse(new URL(url),30000);
        Element element = document.getElementById("J_goodsList");
        System.out.println(element.html());
        //获取所有的li元素
        Elements list = element.getElementsByTag("li");
        list.forEach(li->{
            //src可能还未加载，可以用懒加载source-data-lazy-img
            String img = li.getElementsByTag("img").eq(0).attr("src");
            String price = li.getElementsByClass("p-price").eq(0).text();
            String title = li.getElementsByClass("p-name").eq(0).text();
            System.err.println(img);
            System.err.println(price);
            System.err.println(title);
        });
    }
}
