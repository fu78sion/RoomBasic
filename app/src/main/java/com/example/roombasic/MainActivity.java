package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    //1. 声明变量
    //WordDataBase wordDataBase;
    //WordDao wordDao;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;
    TextView textView;
    //LiveData<List<Word>> allWordsLive;

    //创建viewModel实例
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 获取wordDataBase
        // 三个参数：上下文，database类名，数据库文件名
        // android不允许在主线程操作数据库
        // .allowMainThreadQueries()是强制允许不开线程可以直接用，不推荐
        //wordDataBase = Room.databaseBuilder(this,WordDataBase.class,"word_database")
        //        .allowMainThreadQueries().build();

        //2. 创建Dao
        //wordDao = wordDataBase.getWordDao();

        //获取liveData，实现动态监听，不用手动更新界面
        //allWordsLive = wordDao.getAllWordsLive();
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for (Word word : words) {
                    text.append(word.getId()).append(":").append(word.getWord())
                            .append(":").append(word.getChineseMeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });

        //3. 显示界面
        textView = findViewById(R.id.textView);
        //updateView();

        //添加数据
        buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] english = {"apple","banana","peach"};
                String[] chinese = {"苹果","香蕉","吃个桃桃"};

                for (int i = 0; i < english.length; i++) {
                    wordViewModel.insertWords(new Word(english[i],chinese[i]));
                }
                //Word word = new Word("Hello","你好");
                //Word word2 = new Word("world","世界");
                //wordDao.insertWords(word,word2);
                //updateView();
                //这里是解决主线程无法使用数据库的问题
                //new InsertAsyncTask(wordDao).execute(word,word2);
                //wordViewModel.insertWords(word,word2);
            }
        });

        //清空数据
        buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //wordDao.deleteAllWords();
                //new ClearAsyncTask(wordDao).execute();
                wordViewModel.clearWords();
            }
        });
    }
}

/**
 * 主要学习内容：Entity，Dao，Database
 * 具体步骤：
 * 1. 先添加room依赖
 * 2. 依次添加Entity（实体类），Dao（增删改查），Database
 * 3. 回到mainActivity 写一些操作
 * 4. 添加liveData，在dao里修改返回值类型，在mainActivity中获取liveData，设置observe
 * 5. 将database设置为单例，节省资源
 * 6. 解决不能在主线程中使用数据库的问题AsyncTask，后台执行
 * 7. 添加viewModel，解决mainActivity太臃肿的问题 改的太多，有点懵逼
 * 8. 操作数据的功能应该独立出来，放到repository中
 * 9. 层层封装，刚开始学 学不懂
 *
 * 10. 使用全新控件recyclerView，先添加控件，然后在res中添加配置
 */