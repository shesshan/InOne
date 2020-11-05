package cn.shesshan.myapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.shesshan.myapp.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG="LoginActivity";
    private EditText inputID,inputPwd;
    private Button loginBtn;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }
    public void initView(){
        inputID=findViewById(R.id.inputID);
        inputPwd=findViewById(R.id.inputPwd);
        loginBtn=findViewById(R.id.loginBtn);
        sp = getSharedPreferences("my", Activity.MODE_PRIVATE);
    }
    public void onClick(View btn){
        // 判断逻辑

        // 登录信息写入本地文件
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginID",inputID.getText().toString());
        editor.putString("loginPWD",inputPwd.getText().toString());
        editor.putBoolean("loginStatus",true);
        editor.apply();
        editor.commit();
        // 一次登录
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
