package com.dp.test;

import com.dp.baobao.util.Thumbnail;
import org.junit.Test;

/**
 * Created by dpyang on 2015/1/30.
 */
public class ThumbnailTest extends SuperTest {

    @Test
    public void testGetThumbnail(){
        String soruce="D:\\Documents\\IdeaProject\\jpotato\\weCompany\\out\\artifacts\\WebUI_war_exploded\\upload\\新建文件夹\\Desert.jpg";
        String output="D:\\Documents\\IdeaProject\\jpotato\\weCompany\\out\\artifacts\\WebUI_war_exploded\\upload\\新建文件夹\\abc\\Desert.jpg";
        Thumbnail.getThumbnail(soruce,output,5000,2000);
    }
}
