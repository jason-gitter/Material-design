package com.jinribeidou.material_design;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * Created by Jason on 2019/11/6.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Toast toast = null;
    protected Handler delayHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        MainApp.getInstance().addActivity(this);
        //改变状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            MainApp.getInstance().setStatusBarColor(this, R.color.colorPrimary);
        }
    }

    protected abstract int getLayoutId();

    public void showMsg(final String text) {
        //回到主线程执行run里面的函数
        runOnUiThread(new Runnable() {
            @SuppressLint("ShowToast")
            @Override
            public void run() {
                try {
                    if (toast == null) {
                        toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
                    } else {
                        toast.setText(text);
                    }
                    toast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        delayHandler.removeCallbacksAndMessages(null);
        delayHandler = null;
    }

}
