package com.github.colaalex.cbox.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.colaalex.cbox.R;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.presentation.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Post> data;
    private RecyclerViewClickListener mListener;

    @Inject
    MainAdapter(RecyclerViewClickListener listener) {
        this.data = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_post, viewGroup, false);
        return new ViewHolder(view, mListener);
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
        data.sort((post1, t1) -> {
            if (post1.getPostId() == t1.getPostId())
                return 0;
            return post1.getPostId() < t1.getPostId() ? -1 : 1;
        });
        notifyDataSetChanged();
    }

    int getPostId(int pos) {
        return data.get(pos).getPostId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;

        TextView tvTitle;
        TextView tvBody;

        ViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            mListener = listener;
            tvTitle = view.findViewById(R.id.tvMainTitle);
            tvBody = view.findViewById(R.id.tvMainBody);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}
