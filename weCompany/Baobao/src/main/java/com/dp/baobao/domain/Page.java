package com.dp.baobao.domain;

import java.util.List;

/**
 * Created by dpyang on 2015/1/26.
 */
public interface Page<T> {

    int getTotal();

    List<T> getContent();
}
