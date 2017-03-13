package com.lhg.sangong.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ethan on 2016/6/1.
 * RecyclerView间距设置工具类
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
    }
}
