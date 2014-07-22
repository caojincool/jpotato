package com.lemsun.client.core.mvc.view;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * User: 宗旭东
 * Date: 13-3-14
 * Time: 下午2:26
 */
public class HtmlJsoupTest {

    private static Logger log = LoggerFactory.getLogger(HtmlJsoupTest.class);

    @Test
    public void testHtml() throws IOException {
        File file = new File("E:\\Lemsun\\temp\\TestBS\\index.html");

        Document doc = Jsoup.parse(file, "UTF-8", "http://www.lemsun.com/cs/");

        Elements links = doc.select("a[href]");
        for(Element e : links) {
            String u = e.attr("href");
            String url = e.attr("abs:href");
            if(!StringUtils.equals("#", u) && url.startsWith("http://www.lemsun.com/cs/")) {
                e.attr("href", "test/" + u);
            }
            log.debug("获取一个标签地址 : {}", url);
        }
        FileUtils.writeStringToFile(new File("E:\\Lemsun\\temp\\TestBS\\index.bak.html"), doc.html());
    }

}
