package com.doubleclick.chatapp33;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SeePicturesActivity extends AppCompatActivity {

    ImageView imageView , btnDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_pic);

        imageView = findViewById(R.id.imageView);
        btnDownload =  findViewById(R.id.imageViewDownload);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // nhận dữ liệu thông quan inten mà ta gửi từ màn hình chính
        final String urlImage = getIntent().getStringExtra("urlImage");
        // gọi phuong thức picaso load url của image đến màn hình thông quan imageView đã khai báo
        Picasso.get().load(urlImage).into(imageView);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SeePicturesActivity.this).setTitle("Tải Xuống Hình Ảnh Này")
                        .setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Gọi hàm tải xuốn hình ảnh;
                                DownloadImage(urlImage);
                            }
                        }).show();

            }
        });

    }

    private void DownloadImage(String url) {
        Uri uri = Uri.parse(url);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request  = new DownloadManager.Request(uri);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalFilesDir(this,DIRECTORY_DOWNLOADS,"image.jpg");
        downloadManager.enqueue(request);


    }
}