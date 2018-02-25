package com.familydemo.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.familydemo.R;
import com.familydemo.activity.FriendActivity;
import com.familydemo.activity.RealActivity;
import com.familydemo.activity.VideoActivity;
import com.familydemo.utils.LogUtils;
import com.familydemo.utils.PhotoUtils;
import com.familydemo.utils.ToastUtils;
import com.familydemo.utils.Utils;
import com.familydemo.view.CircleImageView;
import com.familydemo.view.dialog.TakePhotoCallBack;
import com.familydemo.view.dialog.TakePhotoDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class PersonFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private CircleImageView photo_img;
    private LinearLayout video_ll;
    private LinearLayout photos_ll;

    private LinearLayout msg_ll, circle_ll, gift_ll, friends_ll, fav_ll, upload_ll, real_ll, setting_ll;

    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private List<ImageView> imageViews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person, null);
        initView(view);
        imageViews = new ArrayList<>();
        photo_img.setOnClickListener(this);
        video_ll.setOnClickListener(this);
        photos_ll.setOnClickListener(this);

        msg_ll.setOnClickListener(this);
        circle_ll.setOnClickListener(this);
        gift_ll.setOnClickListener(this);
        friends_ll.setOnClickListener(this);
        fav_ll.setOnClickListener(this);
        upload_ll.setOnClickListener(this);
        real_ll.setOnClickListener(this);
        setting_ll.setOnClickListener(this);
        addPhotos();
        return view;
    }

    private void initView(View view) {
        photo_img = view.findViewById(R.id.photo_img);
        video_ll = view.findViewById(R.id.video_ll);
        photos_ll = view.findViewById(R.id.photos_ll);

        msg_ll = view.findViewById(R.id.msg_ll);
        circle_ll = view.findViewById(R.id.circle_ll);
        gift_ll = view.findViewById(R.id.gift_ll);
        friends_ll = view.findViewById(R.id.friends_ll);
        fav_ll = view.findViewById(R.id.fav_ll);
        upload_ll = view.findViewById(R.id.upload_ll);
        real_ll = view.findViewById(R.id.real_ll);
        setting_ll = view.findViewById(R.id.setting_ll);
    }

    private void addPhotos() {
        if (photos_ll.getChildCount() > 0) {
            photos_ll.removeAllViews();
        }
        for (int i = 0; i < imageViews.size(); i++) {
            photos_ll.addView(imageViews.get(i));
        }
        ImageView imageView = new ImageView(getActivity());
        imageView.setPadding(Utils.dip2px(getActivity(), 3),0,Utils.dip2px(getActivity(), 3),0);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(Utils.dip2px(getActivity(), 50),Utils.dip2px(getActivity(), 50)));
        imageView.setImageResource(R.mipmap.btn_takephoto_grey);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TakePhotoDialog dialog = new TakePhotoDialog(getActivity(), R.style.ActionSheetDialogStyle);
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        dialog.showAnim();
                    }
                });
                dialog.setPhotoCallBack(new TakePhotoCallBack() {
                    @Override
                    public void takePhoto() {
                        dialog.cancel();
                        try {
                            autoObtainCameraPermission();
                        } catch (Exception e) {
                            ToastUtils.showShort(getActivity(), "您没有打开拍照权限");
                        }
                    }

                    @Override
                    public void takeAlbum() {
                        dialog.cancel();
                        try {
                            autoObtainStoragePermission();
                        } catch (Exception e) {
                            LogUtils.e(e.toString());
                            ToastUtils.showShort(getActivity(), "您没有打开读取SD卡的权限");
                        }
                    }

                    @Override
                    public void close() {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        photos_ll.addView(imageView);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo_img:
                break;
            case R.id.video_ll:
                startActivity(new Intent(getActivity(), VideoActivity.class));
                break;
            case R.id.photos_ll:
                break;
            case R.id.msg_ll:

                break;
            case R.id.circle_ll:
                break;
            case R.id.gift_ll:
                break;
            case R.id.friends_ll:
                startActivity(new Intent(getActivity(), FriendActivity.class));
                break;
            case R.id.fav_ll:
                break;
            case R.id.upload_ll:
                break;
            case R.id.real_ll:
                startActivity(new Intent(getActivity(), RealActivity.class));
                break;
            case R.id.setting_ll:
                break;
        }
    }
    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                ToastUtils.showShort(getActivity(), "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(getActivity(), "com.familydemo", fileUri);
                }
                PhotoUtils.takePicture(getActivity(), imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.showShort(getActivity(), "设备没有SD卡！");
            }
        }
    }

    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(getActivity(), CODE_GALLERY_REQUEST);
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(getActivity(), "com.familydemo", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(getActivity(), imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.showShort(getActivity(), "设备没有SD卡！");
                    }
                } else {

                    ToastUtils.showShort(getActivity(), "请允许打开相机！！");
                }
                break;


            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(getActivity(), CODE_GALLERY_REQUEST);
                } else {

                    ToastUtils.showShort(getActivity(), "请允许打操作SDCard！！");
                }
                break;
            default:
        }
    }

    private static final int OUTPUT_X = 100;
    private static final int OUTPUT_Y = 100;

    @Override
    //照相以后返回的照片
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(getActivity(), imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    LogUtils.i("访问到相册");
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        LogUtils.i("cropImageUri：" + cropImageUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(getActivity(), data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            try {
                                newUri = FileProvider.getUriForFile(getActivity(), "com.familydemo", new File(newUri.getPath()));
                            } catch (Exception e) {
                                LogUtils.e(e.toString());
                            }
                        }
                        PhotoUtils.cropImageUri(getActivity(), newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtils.showShort(getActivity(), "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    LogUtils.i("开始显示图片");
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, getActivity());
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
                default:
            }
        }

    }

    private void showImages(Bitmap bitmap) {
        ImageView imageView = new ImageView(getActivity());

        imageView.setImageBitmap(bitmap);
        imageView.setPadding(Utils.dip2px(getActivity(), 3),0,Utils.dip2px(getActivity(), 3),0);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(Utils.dip2px(getActivity(), 50),Utils.dip2px(getActivity(), 50)));
        imageViews.add(imageView);
        addPhotos();
    }

}
