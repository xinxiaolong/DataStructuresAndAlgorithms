package java.xiaolong.arithmetic;

import android.Manifest;
import android.app.Activity;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv=findViewById(R.id.tv);
        tv.setText("这个是textView");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.e("sdkVersion",Build.VERSION.SDK_INT +"");
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            Log.e("sdkVersion",Build.VERSION.SDK_INT +" in else");
        }




        mainThreadFun();
    }


    public void mainThreadFun(){


        mPerson=new Person();
        mPerson.name="main Thread person";

        new Thread(runnable).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPerson.name="main Thread notify person";


    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {

            ThreadLocal<Person> threadLocal=new ThreadLocal<Person>(){
                protected Person initialValue() {
                    return new Person();
                }
            };

            Log.e("thread",Thread.currentThread().getName()+"   person    "+threadLocal.get().name);
            threadLocal.set(mPerson);
            Log.e("thread",Thread.currentThread().getName()+"   person    "+threadLocal.get().name);


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("thread",Thread.currentThread().getName()+"   person    "+threadLocal.get().name);
        }
    };

    class Person {
        String name="default name";
    }


    MsgHandler handler=new MsgHandler();

    static class MsgHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
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

        return super.onOptionsItemSelected(item);
    }
}
