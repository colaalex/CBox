package com.github.colaalex.cbox.domain.interactor;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import javax.inject.Inject;

public class CreateInteractor {

    private IPostRepository postRepository;

    @Inject
    CreateInteractor(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void sendPost(Post post, ApiCallback callback) {
        postRepository.sendPost(post, callback);
    }
}
