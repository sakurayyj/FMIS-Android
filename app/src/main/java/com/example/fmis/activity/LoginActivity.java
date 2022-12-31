package com.example.fmis.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fmis.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_REGISTER = 1;
    private static final String TAG = "tag";
    private Button btnLogin;
    private EditText etAccount, etPassword; //账号和密码
    private CheckBox cbRemember;
    private String userName = "a";
    private String pass = "123";
    private TextView register;
    private ImageView visiable;
    private boolean isVisiable = false;
    private TextView before_login;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.register);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        visiable = findViewById(R.id.if_visiable);
        before_login = findViewById(R.id.before_login);
        initPassword();
        validity();  //判断是否填写了账号、密码
        register.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        visiable.setOnClickListener(this);
    }

    private void validity() {
        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                before_login.setVisibility(View.GONE);
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                before_login.setVisibility(View.GONE);
            }
        });
    }

    private void initPassword() {
        //设置密码不可见，设置眼睛标识
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        visiable.setImageResource(R.drawable.invisiable);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, LoginUpActivity.class);
                //Log.d("启动注册","正在启动");
                startActivityForResult(intent, 1);
                //Log.d("启动注册","成功");
                break;
            case R.id.btn_login:
                if(etAccount.getText().toString().equals("") || etPassword.getText().toString().equals("")){
                    before_login.setVisibility(View.VISIBLE);
                    break;
                }
                Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent1);
            case R.id.if_visiable:
                isVisiable = !isVisiable;
                Log.d("is", String.valueOf(isVisiable));
                if(isVisiable){
                    //设置密码为明文，并更改眼睛图标
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    visiable.setImageResource(R.drawable.visiable);
                }else{
                    //设置密码为暗文，并更改眼睛图标
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    visiable.setImageResource(R.drawable.invisiable);
                }
                //设置光标位置的代码需放在设置明暗文的代码后面
                etPassword.setSelection(etPassword.getText().toString().length());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    userName = data.getStringExtra("count");
                    pass = data.getStringExtra("password");
                    Log.d("传输数据",userName+pass);
                    etAccount.setText(userName);
                    etPassword.setText(pass);
                    initPassword();
                }
                break;
            default:
        }
    }
}