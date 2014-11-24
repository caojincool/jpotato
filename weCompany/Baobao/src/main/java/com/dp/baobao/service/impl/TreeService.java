package com.dp.baobao.service.impl;

import com.dp.baobao.dao.TreeDao;
import com.dp.baobao.domain.Tree;
import com.dp.baobao.service.ITreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dpyang on 2014/11/22.
 */
@Service
public class TreeService implements ITreeService {

    private TreeDao treeDao;

    @Autowired
    public TreeService(TreeDao treeDao) {
        this.treeDao = treeDao;
    }

    @Override
    public Tree get(String id) {
        return null;
    }

    @Override
    public Tree getRoot() {
        Tree root=new Tree();
        root.setName("根节点");
        root.setChildren(treeDao.loadChildren(""));
        return root;
    }
}
