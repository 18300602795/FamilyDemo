package com.familydemo.callback;

/**
 * Created by Administrator on 2018\3\1 0001.
 */

public interface PostPhotoCallback {
    void close(int position);

    void add();

    void show(int position);
}
