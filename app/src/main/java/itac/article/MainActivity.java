package itac.article;
//變數命名 InputPassword 物件+用途
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //獲取登入資料
        SharedPreferences LoginData=getSharedPreferences("login",MODE_PRIVATE);
        String username= LoginData.getString("Username", "");

        if (username.equals("")){
            Intent gotoalert = new Intent();//要跳轉頁面要用的物件Intent      並new 出一個實體
            gotoalert.setClass(MainActivity.this, login.class);
            startActivity(gotoalert);//從MainActivity.this   跳轉到Second
            finish();//結束這個Activate

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==R.id.action_alert){
            Intent gotoalert = new Intent();//要跳轉頁面要用的物件Intent,並new 出一個實體
            gotoalert.setClass(MainActivity.this, alert.class);
            startActivity(gotoalert);//從MainActivity.this   跳轉到Second

        }

        return super.onOptionsItemSelected(item);
    }
}
