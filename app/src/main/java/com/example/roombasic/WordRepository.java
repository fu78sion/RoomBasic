package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//其实就是个工具类，把数据库操作放到这里
public class WordRepository {

    private LiveData<List<Word>> allWordsLive;
    private WordDao wordDao;

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    // 初始化，创建WordDataBase以及wordDao
    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getDataBase(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();

        //获取所有数据
        allWordsLive = wordDao.getAllWordsLive();
    }

    public static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
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

    public static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
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

    public static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
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

    public static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
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

    void insertWords(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word... words) {
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words) {
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void clearWords(Word... words) {
        new ClearAsyncTask(wordDao).execute();
    }
}
