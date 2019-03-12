package com.example.pegasus1.camera0308;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** 뷰 변수 객체 선언 */
    private ImageView iv_main;
    private Button btn_camera;
    private Button btn_album;
    private TextView tv_imageview;


    /** 카메라 관련 변수 선언 */
    private final int REQ_CODE_SELECT_IMAGE = 100;
    private final int REQ_CODE_TAKE_PHOTO = 200;
    private Bitmap captureBitmap;
    private Bitmap bitmap_new;
    private ExifInterface exifInterface;
    private Uri uri;
    private File original_file;



    /** 공통 변수 선언 */
    private Context mContext;
    private CommonImage commonImage;
    private int cnt = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 뷰 변수 객체 초기화 */
        iv_main = (ImageView)findViewById(R.id.iv_main);
        btn_camera = (Button)findViewById(R.id.btn_camera);
        btn_album = (Button)findViewById(R.id.btn_album);
        tv_imageview = (TextView)findViewById(R.id.tv_imageview);


        /** 공통 변수 및 함수 초기화 */
        mContext = this;
        commonImage = new CommonImage(mContext);



        /** 사진 촬영 버튼 클릭 이벤트 */
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    //카메라로 넘어가는 인텐트
                startActivityForResult(intent,REQ_CODE_TAKE_PHOTO);
            }
        });

        /** 앨범 선택 버튼 클릭 이벤트*/
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK) ;
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.CONTENT_TYPE) ;
                intent.setType("image/*");
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

            }
        });


        /* 디바이스 회전 시 이미지뷰 초기화 방지 */
        if(savedInstanceState != null)
        {
            cnt=savedInstanceState.getInt("num");
        }




    }


    /**
     * 찍은 사진을 이미지 뷰에 넣는 함수
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != 0){
            /* 사진촬영을 눌렀을 경우 */
            if(requestCode==REQ_CODE_TAKE_PHOTO && !data.equals(null)){


                /* 비트 맵 형식으로 찍은 이미지 가져오기*/
                captureBitmap = (Bitmap) data.getExtras().get("data");

                /* "이미지 넣어주세요" 텍스트 뷰 없앰 */
                if(captureBitmap != null){
                    tv_imageview.setVisibility(View.GONE);
                }

                /* 이미지 뷰에 찍은 이미지 세팅 */
                iv_main.setImageBitmap(captureBitmap);
                iv_main.setScaleType(ImageView.ScaleType.FIT_XY);

                /* 앨범선택을 눌렀을 경우 */
            }else if(requestCode == REQ_CODE_SELECT_IMAGE){

                uri = data.getData();

                /* uri를 파일로 변경 */
                original_file = commonImage.getImageFile(uri);

                try {
                    /* exifInteface 라는 곳에 있는 회전 각도 정보 사용 */
                    exifInterface = new ExifInterface(original_file.getAbsolutePath());
                    bitmap_new = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);

                Bitmap rotate_Bitmap = rotateBitmap(bitmap_new, orientation);
                Log.d("chohee", String.valueOf(bitmap_new));
                Log.d("chohee", "들어옴");


                if(uri != null){
                    tv_imageview.setVisibility(View.GONE);
                }
                iv_main.setImageBitmap(rotate_Bitmap);
                iv_main.setScaleType(ImageView.ScaleType.FIT_XY);
            }


        }


    }



    /**
     * 이미지 회전 방지
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:

                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:

                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:

                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:

                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:

                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:

                matrix.setRotate(90);

                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:

                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:

                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }

        try {
            Log.d("chohee", "여기로 들어옴33");
            Log.d("chohee", String.valueOf(bitmap));
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

            bitmap.recycle();
            return bmRotated;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 디바이스 회전시 이미지뷰에 세팅한 사진이 없어지는 것 방지위함
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        outState.putInt("num", cnt);
    }




}
