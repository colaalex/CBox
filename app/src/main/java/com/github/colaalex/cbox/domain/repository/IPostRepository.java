package com.github.colaalex.cbox.domain.repository;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Post;

public interface IPostRepository {

    void getPost(ApiCallback callback);

    void getComments(int postId, ApiCallback callback);

    void sendPost(Post post, ApiCallback callback);

}
