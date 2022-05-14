package com.example.roombasic;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordDao wordDao;

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    private LiveData<List<Word>> allWordsLive;
    public WordViewModel(@NonNull Application application) {
        super(application);
        WordDataBase wordDataBase = WordDataBase.getDataBase(application);
        wordDao = wordDataBase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }

    void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void clearWords(Word... words){
        new ClearAsyncTask(wordDao).execute();
    }

    public static class InsertAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;

        //操作数据库必须用到wordDao里的方法
        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        //把操作放到后台去执行
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;

        //操作数据库必须用到wordDao里的方法
        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        //把操作放到后台去执行
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;

        //操作数据库必须用到wordDao里的方法
        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        //把操作放到后台去执行
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    public static class ClearAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordDao wordDao;

        //操作数据库必须用到wordDao里的方法
        public ClearAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        //把操作放到后台去执行
        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }
}
