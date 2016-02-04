package itac.article;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2016/1/31.
 */
public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //呼叫父類的OnCreate函式
        setContentView(R.layout.login);

        //連結編輯文字
        final EditText InputPassword=(EditText)findViewById(R.id.InputPassword);
        final EditText InputUsername=(EditText)findViewById(R.id.InputUsername);

        //登入按鈕
        Button ButtonLogin =(Button)findViewById(R.id.ButtonLogin);
        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences LoginData=getSharedPreferences("login",MODE_PRIVATE);
                //帳號密碼符合且不為空
                if(LoginData.getString("Username","").equals(InputUsername.getText().toString()) &&LoginData.getString("Password","").equals(InputPassword.getText().toString()) &&!InputUsername.getText().toString().equals("")){
                    //換頁開始
                    Intent gotoMain=new Intent();
                    gotoMain.setClass(login.this,MainActivity.class);
                    startActivity(gotoMain);
                    //換頁結束
                }
                else{
                    //跳出登入失敗的訊息
                    Toast.makeText(login.this, "Login false", Toast.LENGTH_SHORT).show();
                }

            }
        });//登入按鈕結束

        //註冊按鈕
        Button BuuttonRegister = (Button)findViewById(R.id.ButtonRegister);
        BuuttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor=getSharedPreferences("login",MODE_PRIVATE).edit();//表示要存文件了
                editor.putString("Username",InputUsername.getText().toString()); //告訴第一筆資料要存什麼
                editor.putString("Password",InputPassword.getText().toString()); //第二筆資料要存甚麼
                editor.commit();//儲存

            }
        });//註冊按鈕結束


    }


}
