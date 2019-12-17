package cn.oxframe.statusbar.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.r0adkll.slidr.Slidr;

import java.util.Random;

import cn.oxframe.statusbar.OxStatusbar;

/**
 * Created by Jaeger on 2016/10/15.
 * <p>
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */

public class SwipeBackActivity extends BaseActivity {
    private Button mBtnChangeColor;
    private int mColor = Color.GRAY;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置右滑动返回
        Slidr.attach(this);
        setContentView(R.layout.swipe_back_activity);
        mToolbar = findViewById(R.id.toolbar);
        mBtnChangeColor = findViewById(R.id.btn_change_color);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setBackgroundColor(mColor);
        mBtnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                mColor = 0xff000000 | random.nextInt(0xffffff);
                mToolbar.setBackgroundColor(mColor);
                OxStatusbar.setColorForSwipeBack(SwipeBackActivity.this, mColor, 38);
            }
        });
    }

    @Override
    protected void setStatusBar() {
        OxStatusbar.setColorForSwipeBack(this, mColor, 38);
    }
}
