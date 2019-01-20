package com.github.colaalex.cbox.presentation.post;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.colaalex.cbox.R;
import com.github.colaalex.cbox.domain.entity.Comment;
import com.github.colaalex.cbox.domain.entity.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Comment> data;
    private Post post;

    PostAdapter(List<Comment> comments, Post post) {
        this.data = comments;
        this.post = post;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_post, viewGroup, false);
            return new HeaderViewHolder(view);
        } else if (i == TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_comment, viewGroup, false);
            return new ViewHolder(view);
        }
        throw new RuntimeException("No suitable view holder");
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_HEADER)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) viewHolder).tvBody.setText(post.getBody());
            ((HeaderViewHolder) viewHolder).tvTitle.setText(post.getTitle());
        } else if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).tvBody.setText(data.get(i-1).getBody());
            ((ViewHolder) viewHolder).tvEmail.setText(data.get(i-1).getEmail());
            ((ViewHolder) viewHolder).tvName.setText(data.get(i-1).getName());
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    void loadComments(List<Comment> comments) {
        this.data.addAll(comments);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvEmail;
        TextView tvBody;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPostName);
            tvEmail = itemView.findViewById(R.id.tvPostEmail);
            tvBody = itemView.findViewById(R.id.tvPostBody);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvBody;

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvMainTitle);
            tvBody = itemView.findViewById(R.id.tvMainBody);
        }
    }
}
