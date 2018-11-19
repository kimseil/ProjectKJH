package com.example.lllov.projectkjh;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoActivity extends BaseActivity {
    private Uri uri; //사진을 저장할 경로
    private String currentPath; //실제 사진 파일 경로
    String imageName; //사진 이름
    private final int GALLERY_CODE = 2222;
    private final int CAMERA_CODE = 1111;
    private final int REQUEST_PERMISSION_CODE=100;
    ImageView ivPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 제거
        deleteStatusBar();
        setContentView(R.layout.activity_memo);
    }

    //카메라에서 사진 촬영
    public void takePhoto() {
        //디바이스 장치의 내장 경로 상태
        String state = Environment.getExternalStorageState();
        //SD카드를 사용할 수 있는 상태인지 확인
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            //카메라 호출
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File file = null;
                try {
                    file = createImageFile();
                } catch (IOException e) {
                    Log.e("takePhoto",e.toString());
                }

                if (file != null) {
                    //해당 파일 위치에 있는 contents provider 값을 얻어옴
                    uri = FileProvider.getUriForFile(this, "com.example.lllov.projectkjh", file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, CAMERA_CODE);
                }
            }
        }
    }

    //앨범에서 사진 가져오기
    public void takeAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK).setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI).setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CAMERA_CODE:
                getPictureForPhoto();
                break;
            case GALLERY_CODE:
                sendPicture(data.getData());
                break;
            default:
                break;
        }
    }

    //imageView 클릭 메소드
    public void onClick(View v) {
        if (requestPermission() == true) {
            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    takePhoto();
                }
            };
            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    takeAlbum();
                }
            };

            new AlertDialog.Builder(this)
                    .setTitle("사진 추가")
                    .setPositiveButton("사진 촬영", cameraListener)
                    .setNeutralButton("앨범선택", albumListener)
                    .show();
        }
    }

    public boolean requestPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION_CODE);
            }
        } else{
        }
        return true;
    }

    private File createImageFile() throws IOException{
        File dir = new File(Environment.getExternalStorageDirectory()+"/kjh/");
        if (!dir.exists()){
            dir.mkdir();
        }
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageName = time + ".png";

        File storage = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/kjh/" + imageName);
        currentPath = storage.getAbsolutePath();

        return storage;
    }

    private void getPictureForPhoto(){
        Bitmap bitmap = BitmapFactory.decodeFile(currentPath);
        ExifInterface exifInterface = null;

        try{
            exifInterface = new ExifInterface(currentPath);
        }catch (IOException e){

        }

        int exifOri,exifDe;

        if(exifInterface != null){
            exifOri = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
            exifDe = exifOrientationToDegree(exifOri);
        } else{
            exifDe = 0;
        }
        ivPhoto.setImageBitmap(rotate(bitmap,exifDe));
    }

    private void sendPicture(Uri uri){
        String path = getRealPathFromURI(uri);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ExifInterface exifInterface = null;

        try{
            exifInterface = new ExifInterface(path);
        }catch (IOException e){

        }

        int exifOri = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
        int exifDe = exifOrientationToDegree(exifOri);

        ivPhoto.setImageBitmap(rotate(bitmap,exifDe));
    }

    private int exifOrientationToDegree(int exifOri){
        if (exifOri == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        } else if (exifOri == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        } else if (exifOri == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }

        return 0;
    }

    private Bitmap rotate(Bitmap src, float degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,true);
    }

    private String getRealPathFromURI(Uri uri){
        int index = 0;
        String[] pro = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri,pro,null,null,null);

        if(cursor.moveToFirst()){
            index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }
        return cursor.getString(index);
    }

}
