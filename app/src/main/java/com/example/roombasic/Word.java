package com.example.roombasic;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//标为实体类
@Entity
public class Word {

    //主键，id自动生成
    @PrimaryKey(autoGenerate = true)
    private int id;

    //设置列名
    @ColumnInfo(name = "english_meaning")
    private String word;
    @ColumnInfo(name = "chinese_meaning")
    private String chineseMeaning;

    //有参构造
    public Word(String word, String chineseMeaning) {
        this.word = word;
        this.chineseMeaning = chineseMeaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChineseMeaning() {
        return chineseMeaning;
    }

    public void setChineseMeaning(String chineseMeaning) {
        this.chineseMeaning = chineseMeaning;
    }
}
