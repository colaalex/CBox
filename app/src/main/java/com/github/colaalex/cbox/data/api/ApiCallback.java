package com.github.colaalex.cbox.data.api;

import com.github.colaalex.cbox.domain.entity.Post;

public interface ApiCallback {

    void onSuccess(Post post);

    void onError(Throwable t);
}
