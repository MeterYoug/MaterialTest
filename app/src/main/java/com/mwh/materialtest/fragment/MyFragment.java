package com.mwh.materialtest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mwh.materialtest.Fruit;
import com.mwh.materialtest.FruitAdapter;
import com.mwh.materialtest.R;
import com.mwh.materialtest.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Youg on 2017/1/5 10:50
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private Fruit[] fruits = {new Fruit("苹果", R.drawable.apple), new Fruit("香蕉", R.drawable.banana), new Fruit("橘子", R.drawable.orange), new Fruit("梨", R.drawable.pear)
            , new Fruit("菠萝", R.drawable.pineapple)};
    private List<Fruit> fruitList;
    private FruitAdapter adapter;
    private Context mContext;

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, view);


        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(manager);


        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        fruitList = new ArrayList<>();

        getData();
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
    private void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                } catch (Exception e){
                    e.printStackTrace();
                }
               handler.sendEmptyMessage(1);
            }
        }).start();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    getData();
                    adapter.notifyDataSetChanged();
                    swipeRefresh.setRefreshing(false);
                    break;
            }
        }
    };

}
