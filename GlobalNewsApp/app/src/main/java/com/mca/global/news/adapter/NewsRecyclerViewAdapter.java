package com.mca.global.news.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mca.global.news.NewsModel;
import com.mca.global.news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<NewsModel> models;
    private OnItemClickListener onItemClickListener;
    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsModel> models) {
        this.context = context;
        this.models = models;

    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_news_item, parent, false);
        return new MyViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        NewsModel newsModel = models.get(position);
        holder.txt_title.setText(newsModel.getTitle());
        holder.txt_publishdate.setText(newsModel.getPublishdate());
        holder.txt_summary.setText(newsModel.getSummary());
        Picasso.get().load(newsModel.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=  onItemClickListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_title;
        private ImageView image;
        private TextView txt_publishdate;
        private TextView txt_summary;
        private OnItemClickListener onItemClickListener;

        public MyViewHolder(View view, final OnItemClickListener onItemClickListener) {
            super(view);
            txt_title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
            txt_publishdate = view.findViewById(R.id.publish_date);
            txt_summary = view.findViewById(R.id.description);
            this.onItemClickListener=onItemClickListener;


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,getAdapterPosition());

                }
            });
        }



    }
}
