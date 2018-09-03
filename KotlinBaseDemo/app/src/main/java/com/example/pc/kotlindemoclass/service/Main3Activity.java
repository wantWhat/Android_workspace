package com.example.pc.kotlindemoclass.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pc.kotlindemoclass.Author;
import com.example.pc.kotlindemoclass.Book;
import com.example.pc.kotlindemoclass.R;
import com.example.pc.kotlindemoclass.binarytreedemo.BinraryTreeActivity;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    private Intent intent;
    private Handler mInThreadHandler ;
    Button btn;
    private ServiceConnection mSerConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("demo", "onServiceConnected");
            MyService.MyBinder binder = (MyService.MyBinder)iBinder;
            binder.getSerice();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("demo", "onServiceDisconnected");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn = findViewById(R.id.btn3);
        intent = new Intent(Main3Activity.this, MyService.class);
        Log.i("demo","main thread==" + Thread.currentThread());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                list.add("悬疑");
                list.add("惊悚");
                list.add("搞笑");
                Author author = new Author();
                author.setName("小二");
                author.setAge("25");
                author.setSex("男");
                Book book = new Book();
                book.setName("HEAD OF java");
                book.setPrice("$23");
                book.setAuthor(author);
                book.setKeyWorld(list);
                Intent intent = new Intent(Main3Activity.this, BinraryTreeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",book);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button btn1 = findViewById(R.id.btn4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
            }
        });
        Button btn2 = findViewById(R.id.btn5);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(intent, mSerConn, Context.BIND_AUTO_CREATE);
            }
        });
        Button btn3 = findViewById(R.id.btn6);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("demo","send message");
                mInThreadHandler.sendEmptyMessage(0x01);
                mInThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //运行在handler 所在线程
                        Log.i("demo","thread==" + Thread.currentThread().getName());
                        btn.post(new Runnable() {
                            @Override
                            public void run() {
                                //运行在UI线程
                                btn.setText("btn.post hello");
                            }
                        });
                    }
                });

            }
        });
        new Thread().run();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("demo","new Runnable run");
            }
        });
        thread.start();


        MyThread  thread1  = new MyThread("handlerThread");
        thread1.start();

        SubThread subThread = new SubThread();
        subThread.start();
        new Thread(new MyRunable()).start();

        Handler mHandler = new Handler(thread1.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("demo","msg==" + msg.what + "getThread==" + Thread.currentThread());
            }
        };
        mHandler.sendEmptyMessage(0x01);
    }

    class MyThread extends HandlerThread{
        public MyThread(String name) {
            super(name);
        }

        public MyThread(String name, int priority) {
            super(name, priority);
        }
    }
    class SubThread extends Thread {
        @Override
        public void run() {
            super.run();
            Log.i("demo","SubThread run");
            Main3Activity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("demo","runUIThread" + Thread.currentThread().getName());
                }
            });
        }
    }


    //handler 在子线程创建
    class MyRunable implements Runnable {
        @Override
        public void run() {
            Looper.prepare();
            mInThreadHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.i("wangchao","msg");
                }
            };
            Looper.loop();
            Log.i("demo","MyRunable run");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
        //unbindService(mSerConn);
    }
}
