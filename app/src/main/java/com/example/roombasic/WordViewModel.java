package com.example.roombasic;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordDao wordDao;
    private WordRepository repository;

    public LiveData<List<Word>> getAllWordsLive() {
        return repository.getAllWordsLive();
    }

    //private LiveData<List<Word>> allWordsLive;
    public WordViewModel(@NonNull Application application) {
        super(application);
        //WordDataBase wordDataBase = WordDataBase.getDataBase(application);
        //wordDao = wordDataBase.getWordDao();
        repository = new WordRepository(application);
        //allWordsLive = wordDao.getAllWordsLive();
    }

    void insertWords(Word... words){
        repository.insertWords(words);
    }

    void updateWords(Word... words){
        repository.updateWords(words);
    }

    void deleteWords(Word... words){
        repository.deleteWords(words);
    }

    void clearWords(Word... words){
        repository.clearWords();
    }
}
