package com.github.colaalex.cbox.data.api;

import com.github.colaalex.cbox.domain.entity.Comment;
import com.github.colaalex.cbox.domain.entity.Post;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostApi {

    @GET("/posts/{id}")
    Single<Post> getPost(@Path("id") int postId);

    @GET("/posts")
    Single<List<Post>> getPosts();

    @GET("/comments")
    Single<List<Comment>> getComments(@Query("postId") int postId);

    @POST("/posts")
    Single<Post> sendPost(@Body Post post);

}
