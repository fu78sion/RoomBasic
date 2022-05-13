package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    //1. 声明变量
    WordDataBase wordDataBase;
    WordDao wordDao;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 获取wordDataBase
        // 三个参数：上下文，database类名，数据库文件名
        wordDataBase = Room.databaseBuilder(this,WordDataBase.class,"word_database")
                .allowMainThreadQueries().build();

        //2. 创建Dao
        wordDao = wordDataBase.getWordDao();

        //3. 显示界面
        textView = findViewById(R.id.textView);
        updateView();

        //添加数据
        buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hello","你好");
                Word word2 = new Word("world","世界");
                wordDao.insertWords(word,word2);
                updateView();
            }
        });
    }

    //更新完刷新界面
    public void updateView(){
        List<Word> list = wordDao.getAllWords();
        StringBuilder text = new StringBuilder();
        for (Word word : list) {
            text.append(word.getId()).append(":").append(word.getWord())
                    .append(":").append(word.getChineseMeaning());
        }
        textView.setText(text.toString());
    }
}

/**
 * 主要学习内容：Entity，Dao，Database
 * 具体步骤：
 * 1. 先添加room依赖
 * 2. 依次添加Entity（实体类），Dao（增删改查），Database
 * 3. 回到mainActivity
 */