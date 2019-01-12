package com.github.colaalex.cbox.domain.interactor;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import javax.inject.Inject;

public class PostInteractor {

    private IPostRepository postRepository;

    @Inject
    public PostInteractor(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void getPost(int postId, ApiCallback callback) {
        postRepository.getPost(postId, callback);
    }
}
