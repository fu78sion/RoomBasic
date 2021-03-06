package com.example.roombasic;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. extends RecyclerView.Adapter
 * 2. 自定义viewHolder
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> { // <MyAdapter.MyViewHolder> 重写泛型

    //存放数据
    List<Word> allWords = new ArrayList<>();

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    //用来管理cell_normal
    static class MyViewHolder extends RecyclerView.ViewHolder { //加static 防止内存泄露

        TextView textViewNumber,textViewEnglish,textViewChinese;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //这里绑定又有点不一样了
            textViewNumber = itemView.findViewById(R.id.textView_number);
            textViewEnglish = itemView.findViewById(R.id.textView_english);
            textViewChinese = itemView.findViewById(R.id.textView_chinese);
        }
    }

    //必须要重写的方法，
    //创建viewHolder来呼叫
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //return null;
        //从文件中加载view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);
        return new MyViewHolder(itemView); //创建ViewHolder
    }

    //视图和数据绑定的时候呼叫
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { //position就是当前列表位置
        Word word = allWords.get(position);
        holder.textViewNumber.setText(String.valueOf(position + 1));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseMeaning());
    }

    // 返回总个数
    @Override
    public int getItemCount() {
        return allWords.size(); //返回数量
    }

}
