package itac.article;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by User on 2016/1/31.
 *
 * add server and login/register function
 * but still have some Toast bug in new Thread
 * Commited by sappy5678 on 2016/2/15.
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
        Button ButtonLogin =(Button)findViewById(R.id.ButtonLogin);//連結按鈕
        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //開多線程讓HTTP服務能夠使用
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //未知
                        if(Looper.myLooper()==null){
                            Looper.prepare();
                        }

                        String Username= InputUsername.getText().toString();//穫取輸入字串
                        String Password = InputPassword.getText().toString();//穫取輸入字串
                        String StringLogin="http://apparticle-newsbot.rhcloud.com/login?Username="+Username+"&Password="+Password;//URL預備


                        try {
                            URL UrlLogin= new URL(StringLogin); //轉成URL型態，就是分析URL
                            HttpURLConnection ConnectLogin=(HttpURLConnection)UrlLogin.openConnection();//連結網路(應該吧.....

                            BufferedReader InReaderLogin= new BufferedReader(new InputStreamReader(ConnectLogin.getInputStream(),"UTF-8"));//從網路上抓下來的東西丟到緩衝區去讀
                            String StringRespondLogin = InReaderLogin.readLine();//緩衝區讀到String中
                            Toast.makeText(login.this, StringRespondLogin, Toast.LENGTH_SHORT).show();//顯示氣泡訊息(不過目前有BUG

                            //如果登入成功
                            if("Login Success".equals(StringRespondLogin)){
                                SavePerference(Username,Password);//把帳密儲存到Perference中
                                GotoMain();//到首頁
                            }
                            InReaderLogin.close();//關掉緩衝區
                            ConnectLogin.disconnect();//關閉連結

                            //不懂的例外，推測是URL格式錯誤
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "Login fail", Toast.LENGTH_SHORT).show();//氣泡訊息(BUG

                            //連結錯誤
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "Login fail", Toast.LENGTH_SHORT).show();//氣泡訊息(BUG
                        }



                    }
                }).start();//開始這個線程
                Looper.loop();//未知


            }
        });//登入按鈕結束

        //註冊按鈕
        Button BuuttonRegister = (Button)findViewById(R.id.ButtonRegister);//連結按鈕
        BuuttonRegister.setOnClickListener(new View.OnClickListener() {

            //當按下執行
            @Override
            public void onClick(View v) {


                //開新線程
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //未知
                        if (Looper.myLooper() == null)
                        {
                            Looper.prepare();
                        }

                        //穫取輸入字串
                        String Username=InputUsername.getText().toString();
                        String Password=InputPassword.getText().toString();


                        String StringRegister="http://apparticle-newsbot.rhcloud.com/register?Username="+Username+"&Password="+Password;//URL預備
                        try {
                            URL UrlRegister=new URL(StringRegister);//分析URL
                            HttpURLConnection ConnectRegister = (HttpURLConnection)UrlRegister.openConnection();//連接網路
                            BufferedReader InReaderRegister = new BufferedReader(new InputStreamReader(ConnectRegister.getInputStream(),"UTF-8"));//網路抓下來的"""文字"""放到緩衝區去讀
                            String StringRespond= InReaderRegister.readLine();//讀一行文字
                            Toast.makeText(login.this,StringRespond,Toast.LENGTH_SHORT).show();//氣泡訊息(BUG
                            InReaderRegister.close();//關掉緩衝區
                            ConnectRegister.disconnect();//關閉連結

                            //如果註冊成功
                            if(StringRespond.equals("Register success")/*如果後端回傳Register success*/){
                                SavePerference(Username,Password);//儲存帳號密碼
                                GotoMain();//回到首頁
                            }

                            //URL格式不正確
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "Register fail", Toast.LENGTH_SHORT).show();//氣泡訊息(BUG

                            //連結失敗
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "Register fail", Toast.LENGTH_SHORT).show();//氣泡訊息(BUG
                        }
                    }
                }).start();//開始線程

                //未知
                Looper.loop();



            }
        });//註冊按鈕結束


    }

    //跳轉到MainActivate用的
    private void GotoMain(){

        Intent GotoMain=new Intent(login.this,MainActivity.class);
        startActivity(GotoMain);
        finish();



    }

    //儲存使用者帳號密碼
    private void SavePerference(String Username,String Password){
        SharedPreferences.Editor save = getSharedPreferences("login",MODE_PRIVATE).edit();//表示要存文件了
        save.putString("Username",Username);//告訴第一筆資料要存什麼
        save.putString("Password",Password);//告訴第二筆資料要存什麼
        save.commit();//真正執行儲存

    }


}
