package cn.shesshan.myapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.shesshan.myapp.R;

/**
 * @author shesshan
 */
public class EnterActivity extends AppCompatActivity {
    private SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        sp = getSharedPreferences("my", Activity.MODE_PRIVATE);
        if(sp.getBoolean("loginStatus",false)) {
            // 已登录
            new Handler().postDelayed(new Runnable() {
                // handler时延实现页面跳转
                @Override
                public void run() {
                    Intent mainIntent = new Intent(EnterActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 2500);
        }else
            // 未登录
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Intent loginIntent = new Intent(EnterActivity.this, LoginActivity.class);
                    Intent mainIntent = new Intent(EnterActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 2500);
    }
}
