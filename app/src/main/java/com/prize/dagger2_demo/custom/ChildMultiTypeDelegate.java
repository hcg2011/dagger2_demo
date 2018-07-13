package com.prize.dagger2_demo.custom;

import com.chad.library.adapter.base.util.MultiTypeDelegate;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/7/12 17:59
 */
public class ChildMultiTypeDelegate extends MultiTypeDelegate {
    @Override
    protected int getItemType(Object o) {
        return 0;
    }

}
