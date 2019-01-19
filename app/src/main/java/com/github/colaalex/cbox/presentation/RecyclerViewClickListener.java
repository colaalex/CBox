package com.github.colaalex.cbox.presentation;

import android.support.annotation.Nullable;
import android.view.View;

import com.github.colaalex.cbox.domain.entity.Post;


public interface RecyclerViewClickListener {

    void onClick(View view, int position, @Nullable Post post);
}
