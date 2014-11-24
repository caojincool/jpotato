package com.dp.ui;

import com.dp.baobao.domain.Tree;
import com.dp.baobao.service.ITreeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by dpyang on 2014/11/22.
 */
public class TreeTest extends SuperTest {

    @Autowired
    private ITreeService treeService;

    @Test
    public void testGetRoot(){
        Tree tree=treeService.getRoot();

        getLog().info("根节点名称："+tree.getName());
        List<Tree> children=tree.getChildren();

        for (Tree t:children){
            getLog().info("子节点名称："+t.getName()+"，名称："+t.getCode());
        }
    }
}
