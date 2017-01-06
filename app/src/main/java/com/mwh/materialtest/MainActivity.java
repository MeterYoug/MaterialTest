package com.mwh.materialtest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mwh.materialtest.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_SHIFTING;

/**
 * Material控件
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
//    @BindView(R.id.fab)
//    FloatingActionButton fab;
//    @BindView(R.id.tab_layout)
//    LinearLayout tabLayout;
    @BindView(R.id.bottom_navigation_bar)
BottomNavigationBar bottomNavigationBar;

    private TextView userName;
    private MyPagerAdapter adapter;
    private Context mContext;
    private boolean isHide=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        initToolBar(toolbar, true, "水果");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置菜单按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
        //获得侧滑菜单头布局
        View headerView = navView.getHeaderView(0);
        userName = (TextView) headerView.findViewById(R.id.user_name);
        userName.setText("2342fsd");

        ArrayList<MyFragment> fragments = new ArrayList<>();
        MyFragment fragment1 = MyFragment.newInstance();
        fragment1.setTitle("标题1");
        fragments.add(fragment1);
        MyFragment fragment2 = MyFragment.newInstance();
        fragment2.setTitle("标题2");
        fragments.add(fragment2);
        MyFragment fragment3 = MyFragment.newInstance();
        fragment3.setTitle("标题3");
        fragments.add(fragment3);
        MyFragment fragment4 = MyFragment.newInstance();
        fragment4.setTitle("标题4");
        fragments.add(fragment4);
        MyFragment fragment5 = MyFragment.newInstance();
        fragment5.setTitle("标题5");
        fragments.add(fragment5);
        MyFragment fragment6 = MyFragment.newInstance();
        fragment6.setTitle("标题7");
        fragments.add(fragment6);


        adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        tab.setupWithViewPager(viewPager);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isHide){
//                    bottomNavigationBar.hide();
//                    bottomNavigationBar.hide(true);
//                }else {
//                    bottomNavigationBar.show();
//                    bottomNavigationBar.show(true);//隐藏是否启动动画，这里并不能自定义动画
//                }
//                isHide=!isHide;
//            }
//        });
        //初始化底部导航栏
        initNavigationBar();

    }

    private void initNavigationBar() {
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home_ico, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.data_ico, "数据"))
                .addItem(new BottomNavigationItem(R.drawable.upload_ico, "上传"))
                .addItem(new BottomNavigationItem(R.drawable.syn_ico, "下载"))
                .addItem(new BottomNavigationItem(R.drawable.set_ico, "设置"))
                .setMode(MODE_SHIFTING)
                .initialise();//所有的设置需在调用该方法前完成
        bottomNavigationBar.show();
        bottomNavigationBar.show(true);//隐藏是否启动动画，这里并不能自定义动画
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {//这里也可以使用SimpleOnTabSelectedListener
            @Override
            public void onTabSelected(int position) {//未选中 -> 选中
            }

            @Override
            public void onTabUnselected(int position) {//选中 -> 未选中
            }

            @Override
            public void onTabReselected(int position) {//选中 -> 选中
            }
        });

    }



    /**
     * 初始化 Toolbar
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                //点击菜单按钮打开侧滑菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<MyFragment> fragmentList;

        public MyPagerAdapter(FragmentManager fm, List<MyFragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentList.get(position).getTitle();
        }


        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
