package com.example.fmis.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmis.R;

public class LoginUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private Button back;
    private EditText count;
    private EditText password;
    private EditText password2;
    private CheckBox checkBox;
    private TextView warnings;
    private TextView count_is_none;
    private TextView pass_is_none;
    private TextView less_than_six;
    private TextView qiangdu;
    private View passView;
    private View passView1;
    private View passView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_up);
        register = findViewById(R.id.btn_register);
        back = findViewById(R.id.back);
        count = findViewById(R.id.r_account);
        password = findViewById(R.id.r_password);
        password2 = findViewById(R.id.r_password_Confirm);
        checkBox = findViewById(R.id.cb_agree);
        warnings = findViewById(R.id.warnings);
        qiangdu = findViewById(R.id.mimaqiangdu);
        passView = findViewById(R.id.passwordView);
        passView1 = findViewById(R.id.passwordView1);
        passView2 = findViewById(R.id.passwordView2);
        count_is_none = findViewById(R.id.count_is_none);
        pass_is_none = findViewById(R.id.pass_is_none);
        less_than_six = findViewById(R.id.less_than_six);
        validity();  //判断账号、密码合法性
        register.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    private void validity() {
        count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    count_is_none.setVisibility(View.GONE);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int c, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int c) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(count.getText().toString().equals("")){
                    password.getText().clear();
                    password2.getText().clear();
                    count_is_none.setVisibility(View.VISIBLE);
                }else{
                    count_is_none.setVisibility(View.GONE);
                    if(s.toString().length() > 0) {
                        pass_is_none.setVisibility(View.GONE);
                        if(s.toString().length()<6){
                            less_than_six.setVisibility(View.VISIBLE);
                        }else {
                            less_than_six.setVisibility(View.GONE);
                        }
                        if (s.toString().length() == 1) {
                            qiangdu.setVisibility(View.VISIBLE);
                            passView.setVisibility(View.VISIBLE);
                        }
                        switch (checkPassWord(s.toString())) {
                            case 1:
                                passView1.setVisibility(View.GONE);
                                passView2.setVisibility(View.GONE);
                                break;
                            case 2:
                                passView1.setVisibility(View.VISIBLE);
                                passView2.setVisibility(View.GONE);
                                break;
                            case 3:
                                passView2.setVisibility(View.VISIBLE);
                                break;
                        }
                    }else{
                        qiangdu.setVisibility(View.GONE);
                        passView.setVisibility(View.GONE);
                    }
                }

            }
        });
        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(password.getText().toString().equals("")){
                    s.clear();
                    pass_is_none.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private int checkPassWord(String passwordStr){
        String regexZ = "\\d*";
        String regexS = "[a-zA-Z]+";
        String regexT = "\\W+$";
        String regexZT = "\\D*";
        String regexST = "[\\d\\W]*";
        String regexZS = "\\w*";
        String regexZST = "[\\w\\W]*";

        int pass = 0;
        if (passwordStr.matches(regexZ)) {
            return 1;
        }
        if (passwordStr.matches(regexS)) {
            return 1;
        }
        if (passwordStr.matches(regexT)) {
            return 1;
        }
        if (passwordStr.matches(regexZT)) {
            return 2;
        }
        if (passwordStr.matches(regexST)) {
            return 2;
        }
        if (passwordStr.matches(regexZS)) {
            return 2;
        }
        if (passwordStr.matches(regexZST)) {
            return 3;
        }
        return pass;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                Log.d("button","注册");
                String c = count.getText().toString();
                String p = password.getText().toString();
                String p1 = password2.getText().toString();
                Log.d("获取数据",c+","+p+","+p1);
                if(c.equals("")) {
                    warnings.setText("账号不可为空!");
                    //Toast.makeText(LoginUpActivity.this,"账号不可为空!",Toast.LENGTH_SHORT).show();
                    break;
                }else if(p.equals("")) {
                    warnings.setText("密码不可为空!");
                    //Toast.makeText(LoginUpActivity.this,"密码不可为空!",Toast.LENGTH_SHORT).show();
                    break;
                }else if(!p.equals(p1)) {
                    warnings.setText("两次密码不相同!");
                    //Toast.makeText(LoginUpActivity.this,"两次密码不相同!",Toast.LENGTH_SHORT).show();
                    break;
                }else if(!checkBox.isChecked()) {
                    warnings.setText("请先同意协议!");
                    //Toast.makeText(LoginUpActivity.this,"请先同意协议!",Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("count",c);
                    intent.putExtra("password",p);
                    setResult(RESULT_OK,intent);
                    finish();
                }
//                Intent intent = new Intent();
//                intent.putExtra("count",count.getText().toString());
//                intent.putExtra("password",password.getText().toString());
//                setResult(RESULT_OK,intent);
//                finish();
            case R.id.back:
                finish();
                break;
            }
        }
}