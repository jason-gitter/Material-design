package com.jinribeidou.material_design;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {
    @BindView(R.id.dl_activity_main)
    DrawerLayout dl_activity_main;
    @BindView(R.id.iv_menu)
    ImageView iv_menu;
    private Unbinder unbinder;
    private boolean isDrawerOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取ActionBar对象
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //更换按钮图标（默认是返回的箭头）
            actionBar.setHomeAsUpIndicator(R.drawable.icon_menu);
        }
        unbinder = ButterKnife.bind(this);
        initDrawerListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取menu的注入器(Inflater)并将我们配置的toolbar文件加载到menu中即可
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initDrawerListener() {
        dl_activity_main.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                isDrawerOpen = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                isDrawerOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    @OnClick(R.id.iv_menu)
    void showDrawer() {
        if (isDrawerOpen) {
            dl_activity_main.closeDrawer(GravityCompat.START);
        } else {
            dl_activity_main.openDrawer(GravityCompat.START);
        }
    }

    //重写该方法，箭头菜单按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //弹出左侧滑动菜单
        if(item.getItemId() == R.id.backup){
            Toast.makeText(MainActivity.this, "正在上传中...", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
