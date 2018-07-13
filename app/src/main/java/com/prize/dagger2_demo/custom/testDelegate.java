package com.prize.dagger2_demo.custom;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/7/13 18:21
 */
public class testDelegate extends BaseItemDelegate {

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getItemView() {
        return null;
    }

    @Override
    public int getViewType() {
        return 0;
    }

    @Override
    public void convert(BaseViewHolder holder, MultiItemEntity multiItemEntity, int position) {
    }
}
