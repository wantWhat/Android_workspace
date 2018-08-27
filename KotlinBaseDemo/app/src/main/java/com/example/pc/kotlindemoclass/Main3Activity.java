package com.example.pc.kotlindemoclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button btn = findViewById(R.id.btn3);
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
                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",book);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
