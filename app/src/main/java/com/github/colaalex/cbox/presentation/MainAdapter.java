package com.github.colaalex.cbox.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.colaalex.cbox.R;
import com.github.colaalex.cbox.domain.entity.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Post> data;
    private MainPresenter presenter;

    @Inject
    public MainAdapter(MainPresenter presenter) {
        this.data = new ArrayList<>();
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvBody.setText(data.get(i).getBody());
        viewHolder.tvTitle.setText(data.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void addPost(Post post) {
        data.add(post);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvBody;

        public ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvMainTitle);
            tvBody = view.findViewById(R.id.tvMainBody);
        }
    }
}
