package com.example.petscoffee.ui.pets.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petscoffee.R;
import com.example.petscoffee.model.CoffeeShop;
import com.example.petscoffee.repository.local.Archive;
import com.example.petscoffee.repository.local.CoffeeDatabase;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

/**
 * description ： 登录activity，包含登录（可通过sp记住密码），注册功能。
 * 在该模块创建了roomDatabase的实例
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/22 22:53
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String account;
    private String password;
    private Boolean isSaved = false;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    //控件
    private TextInputLayout account_layout;
    private TextInputLayout password_layout;
    private TextInputEditText accountIn;
    private TextInputEditText passwordIn;
    private MaterialButton button_login;
    private TextView text_forget;
    private TextView text_register;
    private CheckBox checkBox;
    private Dialog register_dialog;//用于全局访问

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //绑定控件
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CoffeeDatabase.createInstance(this);//因为该activity为app的最初activity,再次创建数据库实例
        initView();
        //登陆逻辑部分
        isSaved = sp.getBoolean("isSaved", false);
        String info = sp.getString("data", null);
        if (isSaved) {//是否已经有本地保存了的密码,如果有则在editText中输入数据
            checkBox.setChecked(true);
            account = new Gson().fromJson(info, CoffeeShop.class).getName();
            password = new Gson().fromJson(info, CoffeeShop.class).getPassword();
            accountIn.setText(account);
            passwordIn.setText(password);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button://调用login方法
                login();
                break;
            case R.id.login_forget:
                Toast.makeText(this, "尚未开放该功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_register: {//弹出注册dialog
                register_dialog = new AlertDialog.Builder(this)
                        .setTitle("注册新用户")
                        .setView(initRegisterView())
                        .show();
            }
        }
    }

    private void initView() {//绑定控件
        sp = getSharedPreferences("test", MODE_PRIVATE);
        editor = sp.edit();//用于本地存储已经登陆过的用户账号密码
        account_layout = findViewById(R.id.login_account_layout);
        password_layout = findViewById(R.id.login_password_layout);
        accountIn = findViewById(R.id.login_account_editText);
        passwordIn = findViewById(R.id.login_password_editText);
        button_login = findViewById(R.id.login_button);
        text_forget = findViewById(R.id.login_forget);
        text_register = findViewById(R.id.login_register);
        checkBox = findViewById(R.id.login_checkBox);
        button_login.setOnClickListener(this);
        text_register.setOnClickListener(this);
        text_forget.setOnClickListener(this);
    }

    private void login() {
        {//进行登陆判断
            account = accountIn.getText().toString();
            password = passwordIn.getText().toString();
            if (account.length() == 0) {
                runOnUiThread(() -> account_layout.setError("请输入账号"));
                return;
            }
            if (password.length() == 0) {
                runOnUiThread(() -> password_layout.setError("请输入密码"));
                return;
            }
            Archive.loadCoffee( account, user -> {
                if (user != null) {//如果存在该用户
                    if (this.password.equals(user.getPassword())) {//比对密码
                        //比对成功，进入FirstActivity界面
                        Archive.setId(user.getId());//设置Archive中的user为当前登录的user
                        startActivity(new Intent(LoginActivity.this, FirstActivity.class));
                        if (checkBox.isChecked()) {//是否勾选记住密码
                            //勾选则存储账号密码到本地
                            String data = new Gson().toJson(user);
                            editor.putString("data", data);
                            editor.putBoolean("isSaved", true);
                            editor.apply();
                        } else {
                            isSaved = false;
                            editor.putBoolean("isSaved", false);
                            editor.apply();
                        }
                        finish();//结束该activity
                    } else {//提示错误
                        runOnUiThread(() -> Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show());
                    }
                } else {
                    runOnUiThread(() -> {//该动画必须在主线程运行
                        account_layout.setError("账号不存在，请确认账号是否输入正确或者注册新用户");
                    });
                }
            });
        }
    }

    private View initRegisterView() {//初始化注册dialog中的布局和注册点击监听
        View registerView = LayoutInflater.from(this).inflate(R.layout.dialog_login_register, null);//加载注册界面
        TextInputEditText editText_register_account = registerView.findViewById(R.id.login_register_account_editText);
        TextInputEditText editText_register_password = registerView.findViewById(R.id.login_register_password_editText);
        TextInputEditText editText_register_confirm = registerView.findViewById(R.id.login_register_confirm_editText);
        TextInputEditText editText_register_name = registerView.findViewById(R.id.login_register_name_editText);
        TextInputLayout layout_register_account = registerView.findViewById(R.id.login_register_account_layout);
        TextInputLayout layout_register_password = registerView.findViewById(R.id.login_register_password_layout);
        TextInputLayout layout_register_confirm = registerView.findViewById(R.id.login_register_confirm_layout);
        MaterialButton button_register = registerView.findViewById(R.id.login_register_button);
        button_register.setOnClickListener(view -> {//进行注册逻辑判断
            String register_account = editText_register_account.getText().toString();
            String register_name = editText_register_name.getText().toString();
            String register_password = editText_register_password.getText().toString();
            String register_confirm = editText_register_confirm.getText().toString();
            Archive.loadCoffee(register_account, coffeeShop -> {
                if (coffeeShop == null) {
                    //通过用户名查询数据库，如果不存在该用户则继续
                    if (register_password.equals(register_confirm)) {//检测两次密码输入是否相同
                        CoffeeShop register_coffeeShop = new CoffeeShop(register_account, register_name, register_password);
                        Archive.saveCoffee(register_coffeeShop);
                        runOnUiThread(() -> Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show());
                        register_dialog.dismiss();//关闭dialog
                    } else {//两次密码不相同
                        runOnUiThread(() -> {
                            layout_register_password.setError("两次输入的密码不同，请重新输入");
                            layout_register_confirm.setError("两次输入的密码不同，请重新输入");
                        });

                    }
                } else {//账号已存在
                    runOnUiThread(() -> layout_register_account.setError("该账户已经注册，请重新输入"));
                }
            });
        });
        return registerView;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}