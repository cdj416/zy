package com.zhongyiguolian.zy.myview.waterfallsflow;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import me.tatarka.bindingcollectionadapter2.LayoutManagers;

public class MyLayoutManagers {

    protected MyLayoutManagers() {
    }

    public interface LayoutManagerFactory {
        RecyclerView.LayoutManager create(RecyclerView recyclerView);
    }

    public static LayoutManagers.LayoutManagerFactory flow(int spanCount) {
        return new LayoutManagers.LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new MMStaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
            }
        };
    }
}
