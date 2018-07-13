package com.prize.dagger2_demo.custom;

import android.util.SparseArray;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chaychan.adapter.ItemProviderException;

/**
 * @Description 针对多类型的item，通过代理的形式来分发。代理管理类
 * @Author huangchangguo
 * @Created 2018/7/13 14:51
 */

public class DelegateProvider<T extends MultiItemEntity, K extends BaseItemDelegate>{
    private static final int DEFAULT_VIEW_TYPE = -100;
    private SparseArray<K> delegates = new SparseArray<>();

    public DelegateProvider registerDelegate(K delegate) {
        cheakRegister(delegate);
        int viewType = delegate.getViewType();
        if (delegates.get(viewType) == null) {
            delegates.put(viewType, delegate);
        }
        return this;
    }

    private void cheakRegister(K delegate) {
        cheackDelegate(delegate);
        int viewType = delegate.getViewType();
        if (delegates.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegate is "
                            + delegates.get(viewType));
        }
    }

    private void cheackDelegate(K delegate) {
        if (delegate == null) {
            throw new ItemProviderException("ItemProvider can not be null");
        }
    }

    public SparseArray<K> getItemDelegates() {
        return delegates;
    }

    public int getDelegateCount() {
        return delegates.size();
    }

    public DelegateProvider<T, K> unRegisterDelegate(K delegate) {
        cheackDelegate(delegate);
        int indexToRemove = delegates.indexOfValue(delegate);
        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public DelegateProvider<T, K> unRegisterDelegate(int itemType) {
        int indexToRemove = delegates.indexOfKey(itemType);
        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * viewType
     *
     * @param item
     * @return
     */
    public int getItemViewType(T item) {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            K delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item)) {
                return delegates.keyAt(i);
            }
        }
        return DEFAULT_VIEW_TYPE;
    }

    public int getDefItemType() {
        return DEFAULT_VIEW_TYPE;
    }

    public Boolean isDefItemType(int type) {
        return type == DEFAULT_VIEW_TYPE;
    }

    public K convert(BaseViewHolder holder, T item, int position) {
        int viewType = holder.getItemViewType();
        K delegate = delegates.get(viewType);
        delegate.convert(holder, item, position);
        return delegate;
    }


    public K getItemViewDelegate(int viewType) {
        return delegates.get(viewType);
    }

    public int getItemLayoutId(int viewType) {
        return getItemViewDelegate(viewType).getLayoutId();
    }

    public int getItemViewType(K delegate) {
        return delegates.indexOfValue(delegate);
    }
}
