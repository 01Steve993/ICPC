package com.example.icpc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<discover_article> articleList;

    public ArticleAdapter(List<discover_article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        discover_article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.source.setText(article.getSource());
        holder.date.setText(article.getDate());
        holder.image.setImageResource(article.getImageResource());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title, source, date;
        ImageView image;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            source = itemView.findViewById(R.id.itemSource);
            date = itemView.findViewById(R.id.itemDate);
            image = itemView.findViewById(R.id.itemImage);
        }
    }
}
