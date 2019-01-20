package com.github.colaalex.cbox.data.repository;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.data.api.PostApi;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Repository implements IPostRepository {

    private PostApi postApi;

    @Inject
    public Repository(PostApi postApi) {
        this.postApi = postApi;
    }

    @Override
    public Disposable getPost(ApiCallback callback) {
        return postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }

    @Override
    public Disposable getComments(int postId, ApiCallback callback) {
        return postApi.getComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }

    @Override
    public Disposable sendPost(Post post, ApiCallback callback) {
        return postApi.sendPost(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }
}
