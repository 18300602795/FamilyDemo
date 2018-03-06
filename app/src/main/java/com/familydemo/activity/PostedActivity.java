package com.familydemo.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.adapter.PostPhotoAdapter;
import com.familydemo.callback.PostPhotoCallback;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by Administrator on 2018\2\28 0028.
 */

public class PostedActivity extends BaseActivity implements View.OnClickListener, PostPhotoCallback {
    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    private ImageView title_back;
    private TextView title_tv;
    private TextView title_right;
    private RecyclerView photo_recycle;
    private List<String> bitmaps;
    private ArrayList<String> mSelectPath;
    private PostPhotoAdapter photoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.title_bg), 0);
        initView();
        initDate();
    }

    private void initView() {
        title_back = findViewById(R.id.title_back);
        title_tv = findViewById(R.id.title_tv);
        title_right = findViewById(R.id.title_right_tv);
        photo_recycle = findViewById(R.id.photo_recycle);
    }

    private void initDate() {
        title_back.setOnClickListener(this);
        title_right.setOnClickListener(this);
        title_tv.setText("发帖");
        title_right.setVisibility(View.VISIBLE);
        title_right.setText("发布");
        photo_recycle.setLayoutManager(new GridLayoutManager(context, 5));
        bitmaps = new ArrayList<>();
        photoAdapter = new PostPhotoAdapter(bitmaps, context);
        photoAdapter.setPhotoCallback(this);
        photo_recycle.setAdapter(photoAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_right_tv:
                break;
        }

    }

    @Override
    public void close(int position) {
        bitmaps.remove(position);
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void add() {
        pickImage();
    }

    @Override
    public void show(int position) {
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            int maxNum = 9;
            MultiImageSelector selector = MultiImageSelector.create(context);
            selector.showCamera(true);
            selector.count(maxNum);
            selector.multi();
            selector.origin(mSelectPath);
            selector.start(context, REQUEST_IMAGE);
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                bitmaps.clear();
                for (String p : mSelectPath) {
                    bitmaps.add(p);
                }
                photoAdapter.notifyDataSetChanged();
            }
        }
    }
}
