package com.github.colaalex.cbox.data.api;

public interface ApiCallback {

    void onSuccess(Object result);

    void onError(Throwable t);
}
