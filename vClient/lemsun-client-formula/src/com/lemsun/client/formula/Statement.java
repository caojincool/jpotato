package com.lemsun.client.formula;

import com.lemsun.client.core.formula.FOperater;

/**
 * 公式片段
 * User: 宗旭东
 * Date: 13-10-16
 * Time: 上午10:20
 */
public class Statement {

    private Formula fleft;
    private Formula fright;
    private FOperater operater;

    /**
     * 获取左边的公式对象, 通常为设置对象
     */
    public Formula getFleft() {
        return fleft;
    }

    public void setFleft(Formula fleft) {
        this.fleft = fleft;
    }

    /**
     * 获取右边的公式对象, 通常为获取对象
     */
    public Formula getFright() {
        return fright;
    }

    public void setFright(Formula fright) {
        this.fright = fright;
    }

    /**
     * 两个公式间的操作符
     */
    public FOperater getOperater() {
        return operater;
    }

    public void setOperater(FOperater operater) {
        this.operater = operater;
    }
}
