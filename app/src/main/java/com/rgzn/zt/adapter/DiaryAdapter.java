package com.rgzn.zt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rgzn.zt.R;
import com.rgzn.zt.entity.DiaryItem;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {
    private List<DiaryItem> mDiaryList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView theme;
        TextView content;
        TextView addTime;
        ImageView photo;

        public ViewHolder(View view) {
            super(view);
            theme = (TextView) view.findViewById(R.id.diary_theme);
            content = (TextView) view.findViewById(R.id.diary_content);
            addTime = (TextView) view.findViewById(R.id.diary_addtime);
            photo = (ImageView) view.findViewById(R.id.diary_photo);
        }
    }

    public DiaryAdapter(List<DiaryItem> diaryList) {
        mDiaryList = diaryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiaryItem diaryItem = mDiaryList.get(position);
        holder.theme.setText(diaryItem.getTheme());
        holder.content.setText(diaryItem.getContent());
        holder.addTime.setText(diaryItem.getAddtime());
        holder.photo.setImageBitmap(diaryItem.getPhoto());
    }

    @Override
    public int getItemCount() {
        return mDiaryList.size();
    }
}
