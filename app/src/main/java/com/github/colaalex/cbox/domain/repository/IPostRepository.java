package com.github.colaalex.cbox.domain.repository;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Post;

import io.reactivex.disposables.Disposable;

public interface IPostRepository {

    Disposable getPost(ApiCallback callback);

    Disposable getComments(int postId, ApiCallback callback);

    Disposable sendPost(Post post, ApiCallback callback);

}
