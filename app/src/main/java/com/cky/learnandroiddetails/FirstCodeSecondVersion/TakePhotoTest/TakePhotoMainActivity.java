package com.cky.learnandroiddetails.FirstCodeSecondVersion.TakePhotoTest;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



/*
*
* 调用系统已有的相机应用拍照根据如何处理照片可以分两种情况：
1.拍完之后，图片数据通过onActivityResult(int requestCode, int resultCode, Intent data)回调函数里面的data参数带回来，当然前提是通过startActivityForResult（）方法启动相机应用的。
2.启动相机应用的时候，将要存储图片数据的文件以uri形式传递给相机应用，这样相应拍完之后，图片就会存储到你指定的位置。
* */


public class TakePhotoMainActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    private ImageView mImageView;
    private Uri mUri;
    private Button mButton1;
    private Button mButton2;

    private static final String TAG = "TakePhotoMainActivity1";
    private static final String AUTHORITY = "com.cky.learnandroiddetails.FirstCodeSecondVersion.TakePhotoTest.fileprovider";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo_main);

        mButton1 = (Button) findViewById(R.id.button1);
        mImageView = (ImageView) findViewById(R.id.imageview);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(TakePhotoMainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TakePhotoMainActivity.this, new String[] {Manifest.permission.CAMERA}, TAKE_PHOTO);
                } else {
                    takePhoto();
                }
            }
        });

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(TakePhotoMainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TakePhotoMainActivity.this,
                            new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CHOOSE_PHOTO);
                } else {
                    openAlbum();
                }
            }
        });
    }

    private void takePhoto() {
        Log.d(TAG, "getExternalCacheDir " + getExternalCacheDir().toString());

        //应用关联缓存目录,6.0后存储在这个目录不需要权限申请,而存在sd卡其他目录都需要申请权限
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // >= 7.0
        /*
        if (Build.VERSION.SDK_INT >= 24) {
            mUri = FileProvider.getUriForFile(this, AUTHORITY, outputImage);

        } else {
            mUri = Uri.fromFile(outputImage);
        }
        */
        mUri = FileProvider.getUriForFile(this, AUTHORITY, outputImage);
        Log.d(TAG, "mUri " + mUri.toString());


        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, TAKE_PHOTO);

    }


    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (TAKE_PHOTO == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto();
            } else {

            }
        } else if (CHOOSE_PHOTO == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                try {

                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mUri));
                    //Bitmap bitmap = BitmapFactory.decodeFile(mUri.getPath());
                    //Log.d(TAG, "mUri.getPath()" + mUri.getPath());
                    mImageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

            }
        } else if (CHOOSE_PHOTO == requestCode) {
            if (resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImageOnKitKat(data);
                } else {
                    handleImageBeforeKitKat(data);
                }

            }
        }
    }

    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }

        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }

            cursor.close();
        }

        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mImageView.setImageBitmap(bitmap);
        } else {
            ToastUtil.showToast(this, "获取图片失败");
        }
    }
}
