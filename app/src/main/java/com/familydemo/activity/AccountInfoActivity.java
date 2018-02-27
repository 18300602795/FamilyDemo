package com.familydemo.activity;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familydemo.R;
import com.familydemo.base.Constant;
import com.familydemo.utils.LogUtils;
import com.familydemo.utils.PhotoUtils;
import com.familydemo.utils.ToastUtils;
import com.familydemo.view.CircleImageView;
import com.familydemo.view.dialog.TakePhotoCallBack;
import com.familydemo.view.dialog.TakePhotoDialog;
import com.jaeger.library.StatusBarUtil;

import java.io.File;

import static com.familydemo.base.Constant.CAMERA_PERMISSIONS_REQUEST_CODE;
import static com.familydemo.base.Constant.CODE_CAMERA_REQUEST;
import static com.familydemo.base.Constant.CODE_GALLERY_REQUEST;
import static com.familydemo.base.Constant.CODE_RESULT_REQUEST;

/**
 * Created by Administrator on 2018\2\26 0026.
 */

public class AccountInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView title_back;
    private TextView title_tv;

    private LinearLayout account_ll, photo_ll, nick_ll, sex_ll, area_ll,
            album_ll, record_ll, friend_ll, game_ll, home_ll, exit_ll;
    private CircleImageView photo_img;

    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.title_bg), 0);
        initView();
        initDate();
    }


    private void initView() {
        title_back = findViewById(R.id.title_back);
        title_tv = findViewById(R.id.title_tv);

        account_ll = findViewById(R.id.account_ll);
        photo_ll = findViewById(R.id.photo_ll);
        nick_ll = findViewById(R.id.nick_ll);
        sex_ll = findViewById(R.id.sex_ll);
        area_ll = findViewById(R.id.area_ll);
        album_ll = findViewById(R.id.album_ll);
        record_ll = findViewById(R.id.record_ll);
        friend_ll = findViewById(R.id.friend_ll);
        game_ll = findViewById(R.id.game_ll);
        home_ll = findViewById(R.id.home_ll);
        exit_ll = findViewById(R.id.exit_ll);
        photo_img = findViewById(R.id.photo_img);
    }

    private void initDate() {
        title_tv.setText("我的信息");
        title_back.setOnClickListener(this);

        account_ll.setOnClickListener(this);
        photo_ll.setOnClickListener(this);
        nick_ll.setOnClickListener(this);
        sex_ll.setOnClickListener(this);
        area_ll.setOnClickListener(this);
        album_ll.setOnClickListener(this);
        record_ll.setOnClickListener(this);
        friend_ll.setOnClickListener(this);
        game_ll.setOnClickListener(this);
        home_ll.setOnClickListener(this);
        exit_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.account_ll:
                break;
            case R.id.photo_ll:
                final TakePhotoDialog dialog = new TakePhotoDialog(context, R.style.ActionSheetDialogStyle, "设置头像");
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
                            ToastUtils.showShort(context, "您没有打开拍照权限");
                        }
                    }

                    @Override
                    public void takeAlbum() {
                        dialog.cancel();
                        try {
                            autoObtainStoragePermission();
                        } catch (Exception e) {
                            LogUtils.e(e.toString());
                            ToastUtils.showShort(context, "您没有打开读取SD卡的权限");
                        }
                    }

                    @Override
                    public void close() {
                        dialog.cancel();
                    }
                });
                dialog.show();
                break;
            case R.id.nick_ll:
                break;
            case R.id.sex_ll:
                break;
            case R.id.area_ll:
                break;
            case R.id.album_ll:
                break;
            case R.id.record_ll:
                break;
            case R.id.friend_ll:
                break;
            case R.id.game_ll:
                break;
            case R.id.home_ll:
                startActivity(new Intent(context, HomeActivity.class));
                break;
            case R.id.exit_ll:
                break;
        }
    }

    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA)) {
                ToastUtils.showShort(context, "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(context, "com.familydemo", fileUri);
                }
                PhotoUtils.takePicture(context, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.showShort(context, "设备没有SD卡！");
            }
        }
    }

    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(context, CODE_GALLERY_REQUEST);
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
                            imageUri = FileProvider.getUriForFile(context, "com.familydemo", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(context, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.showShort(context, "设备没有SD卡！");
                    }
                } else {

                    ToastUtils.showShort(context, "请允许打开相机！！");
                }
                break;


            }
            //调用系统相册申请Sdcard权限回调
            case Constant.STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(context, CODE_GALLERY_REQUEST);
                } else {

                    ToastUtils.showShort(context, "请允许打操作SDCard！！");
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
                case Constant.CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(context, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, Constant.CODE_RESULT_REQUEST);
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    LogUtils.i("访问到相册");
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        LogUtils.i("cropImageUri：" + cropImageUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(context, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            try {
                                newUri = FileProvider.getUriForFile(context, "com.familydemo", new File(newUri.getPath()));
                            } catch (Exception e) {
                                LogUtils.e(e.toString());
                            }
                        }
                        PhotoUtils.cropImageUri(context, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtils.showShort(context, "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    LogUtils.i("开始显示图片");
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, context);
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
                default:
            }
        }

    }

    private void showImages(Bitmap bitmap) {
        photo_img.setImageBitmap(bitmap);
    }
}
