package com.example.roombasic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//singleton
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {

    //设置单例模式
    private static WordDataBase INSTANCE;
    //private WordDataBase(){};

    //线程同步
    public static synchronized WordDataBase getDataBase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordDataBase.class,"word_database").build();
        }

        //完成单例模式设计
        return INSTANCE;
    }

    //获取dao对象
    public abstract WordDao getWordDao();
}
