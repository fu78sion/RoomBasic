package com.example.roombasic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Dao就是访问数据库操作的接口 增删改查之类的
@Dao
public interface WordDao {

    @Insert
    void insertWords(Word... words);

    @Update
    void updateWords(Word... words);

    @Delete
    void deleteWords(Word... words);

    //删除所有数据
    @Query("DELETE FROM Word")
    void deleteAllWords();

    //查询所有数据
    @Query("SELECT * FROM Word ORDER BY ID DESC")
    //List<Word> getAllWords();
    //这里的代码生成的时候事务直接帮我写好，很爽
    LiveData<List<Word>> getAllWordsLive();
}
