package com.example.mvvmweekend.view.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmweekend.R;
import com.example.mvvmweekend.data.models.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    Context context;
    List<Article> articleList;
    OnItemClickListener listener;

    public ArticleAdapter(Context context, List<Article> articleList, OnItemClickListener listener) {
        this.context = context;
        this.articleList = articleList;
        this.listener = listener;
    }

    public void setData(List<Article> articles){
        this.articleList.clear();
        this.articleList.addAll(articles);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        Glide.with(context).load(article.getImage_url()).into(holder.image);

        holder.image.setOnClickListener(view -> {
            listener.onArticleClicked(article);
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }
    public interface OnItemClickListener{
        void onArticleClicked(Article article);
    }
}
