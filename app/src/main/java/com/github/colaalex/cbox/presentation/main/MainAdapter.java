package com.github.colaalex.cbox.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.colaalex.cbox.R;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.presentation.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Post> data;
    private RecyclerViewClickListener mListener;

    @Inject
    MainAdapter(RecyclerViewClickListener listener) {
        this.data = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_header_main, viewGroup, false);
            return new HeaderViewHolder(view, mListener);
        }
        else if (i == TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_post, viewGroup, false);
            return new ItemViewHolder(view, mListener);
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
        if (viewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) viewHolder).tvBody.setText(data.get(i-1).getBody());
            ((ItemViewHolder) viewHolder).tvTitle.setText(data.get(i-1).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
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

    void addPosts(List<Post> posts) {
        data.addAll(posts);
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;

        TextView tvTitle;
        TextView tvBody;

        ItemViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            mListener = listener;
            tvTitle = view.findViewById(R.id.tvMainTitle);
            tvBody = view.findViewById(R.id.tvMainBody);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition(), data.get(getAdapterPosition() - 1));
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button newPostButton;
        private RecyclerViewClickListener mListener;

        HeaderViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            newPostButton = itemView.findViewById(R.id.btnNewPost);
            newPostButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition(), null);
        }
    }
}
