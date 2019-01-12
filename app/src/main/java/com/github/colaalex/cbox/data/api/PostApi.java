package com.github.colaalex.cbox.data.api;

import com.github.colaalex.cbox.domain.entity.Post;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostApi {

    @GET("/posts/{id}")
    Single<Post> getPost(@Path("id") int postId);

}
