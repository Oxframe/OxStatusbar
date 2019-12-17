package cn.oxframe.statusbar.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import cn.oxframe.statusbar.OxStatusbar;

/**
 * Created by Jaeger on 16/2/14.
 * <p>
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */
public class BaseActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
        this.mContext = this;
    }

    protected void setStatusBar() {
        OxStatusbar.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public Drawable iDrawable(int id) {
        return ContextCompat.getDrawable(mContext, id);
    }

}
