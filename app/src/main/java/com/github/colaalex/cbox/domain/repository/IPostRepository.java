package com.github.colaalex.cbox.domain.repository;

import com.github.colaalex.cbox.data.api.ApiCallback;

public interface IPostRepository {

    void getPost(int postId, ApiCallback callback);

    void getComments(int postId, ApiCallback callback);

}
