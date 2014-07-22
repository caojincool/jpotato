package com.lemsun.client.core.mvc.view;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 * User: 宗旭东
 * Date: 13-3-12
 * Time: 上午10:35
 */
public class JsTemplateTest {

    private static Logger log = LoggerFactory.getLogger(JsTemplateTest.class);

    @Test
    public void testStatement() throws IOException {

        String context = FileUtils.readFileToString(new File("E:\\Lemsun\\temp\\template.jsp"));


        JsTemplate template = new JsTemplate(context);

        String t = template.parser();

        System.out.println(t);

        System.out.println("-------------");

        System.out.println(template.isScript());
    }

    @Test
    public void testJs() {


    }

}
