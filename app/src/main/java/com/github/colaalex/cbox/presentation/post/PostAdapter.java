package com.github.colaalex.cbox.presentation.post;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.colaalex.cbox.R;
import com.github.colaalex.cbox.domain.entity.Comment;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Comment> data;

    PostAdapter(List<Comment> comments) {
        this.data = comments;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_comment, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvBody.setText(data.get(i).getBody());
        viewHolder.tvEmail.setText(data.get(i).getEmail());
        viewHolder.tvName.setText(data.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void loadComments(List<Comment> comments) {
        this.data = comments;
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
}
