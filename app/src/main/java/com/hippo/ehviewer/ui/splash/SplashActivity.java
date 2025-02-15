package com.hippo.ehviewer.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hippo.ehviewer.R;
import com.hippo.ehviewer.ui.EhActivity;
import com.hippo.ehviewer.ui.MainActivity;
import com.hippo.ehviewer.ui.dialog.EhDistributeListener;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;


public class SplashActivity extends EhActivity {


    @Override
    protected int getThemeResId(int theme) {
        return R.style.SplashTheme;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Distribute.setListener(new EhDistributeListener());
        AppCenter.start(getApplication(), "a47010fb-702a-415a-ad93-ab5c674093ca",
                Analytics.class, Crashes.class, Distribute.class);
        Distribute.setEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        new Thread(() -> {
            //耗时任务，比如加载网络数据
            runOnUiThread(() -> {
                //跳转至 MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                //结束当前的 Activity
                SplashActivity.this.finish();
            });
        }).start();
    }
}
