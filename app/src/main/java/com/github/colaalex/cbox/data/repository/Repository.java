package com.github.colaalex.cbox.data.repository;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.data.api.PostApi;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Repository implements IPostRepository {

    private PostApi postApi;

    private List<Post> posts;

    @Inject
    public Repository(PostApi postApi) {
        this.postApi = postApi;
        posts = new ArrayList<>();
    }


    @Override
    public void getPost(int postId, ApiCallback callback) {
        postApi.getPost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }

}
