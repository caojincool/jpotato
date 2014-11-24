package com.dp.baobao.service;

import com.dp.baobao.domain.Tree;

/**
 * Created by dpyang on 2014/11/22.
 */
public interface ITreeService {

    /**
     * 根据节点编码获取节点
     * @param id
     * @return
     */
    Tree get(String id);

    Tree getRoot();
}
