package com.github.colaalex.cbox.data.api;

import com.github.colaalex.cbox.domain.entity.Comment;
import com.github.colaalex.cbox.domain.entity.Post;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @FormUrlEncoded
    @POST("/posts")
    Single<Post> sendPost(@Field("title") String title, @Field("body") String body, @Field("userId") int userId);

}
