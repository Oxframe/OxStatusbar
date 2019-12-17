package cn.oxframe.statusbar.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import cn.oxframe.statusbar.OxStatusbar;

/**
 * Created by Jaeger on 16/2/14.
 * <p>
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */
public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private CheckBox mChbTranslucent;
    private Button mBtnSetColor;
    private Button mBtnSetTransparent;
    private Button mBtnSetTranslucent;
    private Button mBtnSetForImageView;
    private Button mBtnUseInFragment;
    private Button mBtnSetColorForSwipeBack;
    private Button mBtnSwitchMode;

    private ViewGroup contentLayout;
    private SeekBar mSbChangeAlpha;
    private TextView mTvStatusAlpha;

    private int mStatusBarColor;
    private int mAlpha = OxStatusbar.DEFAULT_STATUS_BAR_ALPHA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        contentLayout = findViewById(R.id.main);
        mToolbar = findViewById(R.id.toolbar);
        mChbTranslucent = findViewById(R.id.chb_translucent);
        mBtnSetColor = findViewById(R.id.btn_set_color);
        mBtnSetTransparent = findViewById(R.id.btn_set_transparent);
        mBtnSetTranslucent = findViewById(R.id.btn_set_translucent);
        mBtnSetForImageView = findViewById(R.id.btn_set_for_image_view);
        mBtnUseInFragment = findViewById(R.id.btn_use_in_fragment);
        mBtnSetColorForSwipeBack = findViewById(R.id.btn_set_color_for_swipe_back);
        mBtnSwitchMode = findViewById(R.id.btn_switch_mode);
        mSbChangeAlpha = findViewById(R.id.sb_change_alpha);
        mTvStatusAlpha = findViewById(R.id.tv_status_alpha);
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mBtnSetColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ColorStatusBarActivity.class);
                startActivity(intent);
            }
        });

        mBtnSetTransparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageStatusBarActivity.class);
                intent.putExtra(ImageStatusBarActivity.EXTRA_IS_TRANSPARENT, true);
                startActivity(intent);
            }
        });

        mBtnSetTranslucent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageStatusBarActivity.class);
                intent.putExtra(ImageStatusBarActivity.EXTRA_IS_TRANSPARENT, false);
                startActivity(intent);
            }
        });

        mBtnSetForImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);
                startActivity(intent);
            }
        });

        mBtnUseInFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UseInFragmentActivity.class);
                startActivity(intent);
            }
        });

        mBtnSetColorForSwipeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SwipeBackActivity.class);
                startActivity(intent);
            }
        });

        mBtnSwitchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SwitchModeActivity.class);
                startActivity(intent);
            }
        });

        mChbTranslucent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChbTranslucent.isChecked()) {
                    contentLayout.setBackground(iDrawable(R.drawable.bg_monkey));
                    OxStatusbar.setTranslucentForDrawerLayout(MainActivity.this, mDrawerLayout, mAlpha);
                    mToolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                } else {
                    contentLayout.setBackground(null);
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    OxStatusbar.setColorForDrawerLayout(MainActivity.this, mDrawerLayout, getResources().getColor(R.color.colorPrimary), mAlpha);
                }
            }
        });

        mSbChangeAlpha.setMax(255);
        mSbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                if (mChbTranslucent.isChecked()) {
                    OxStatusbar.setTranslucentForDrawerLayout(MainActivity.this, mDrawerLayout, mAlpha);
                } else {
                    OxStatusbar.setColorForDrawerLayout(MainActivity.this, mDrawerLayout, mStatusBarColor, mAlpha);
                }
                mTvStatusAlpha.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSbChangeAlpha.setProgress(OxStatusbar.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Override
    protected void setStatusBar() {
        mStatusBarColor = getResources().getColor(R.color.colorPrimary);
        OxStatusbar.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer_layout), mStatusBarColor, mAlpha);
    }
}
