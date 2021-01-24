package com.example.mvvmweekend.view.mutableArticle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mvvmweekend.R;
import com.example.mvvmweekend.data.models.ImageResponse;
import com.example.mvvmweekend.data.models.modelRequest.ArticleRequest;
import com.example.mvvmweekend.helpers.FilePath;

import java.io.File;

public class MutableActivity extends AppCompatActivity {
    private static final String TAG = MutableActivity.class.getSimpleName();
    ProgressBar progressBar;
    EditText title, description;
    ImageView image;
    Button btnSave;
    Uri imageUri;
    MutableViewModel viewModel;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutable);

        initUI();
        requestPermission();
        viewModel = ViewModelProviders.of(this).get(MutableViewModel.class);
        viewModel.init();

        image.setOnClickListener(view -> {
            showFileChooser();
        });
        btnSave.setOnClickListener(view -> {
            viewModel.postArticle(new ArticleRequest(title.getText().toString(),
                    description.getText().toString(),
                    imageUrl))
            .observe(this, new Observer<Void>() {
                @Override
                public void onChanged(Void aVoid) {
                    Toast.makeText(MutableActivity.this, "Article Posted", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"),1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000 && resultCode == RESULT_OK && data != null){
            progressBar.setVisibility(View.VISIBLE);
            imageUri = data.getData();
            Log.d(TAG, "onActivityResult: "+ imageUri);
            try{
                String selectedFilePath = FilePath.getPath(MutableActivity.this, imageUri);
                File file = new File(selectedFilePath);
                formDataConverter(file);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void formDataConverter(File file) {
        viewModel.uploadImage(file).observe(this, new Observer<ImageResponse>() {
            @Override
            public void onChanged(ImageResponse imageResponse) {
                progressBar.setVisibility(View.INVISIBLE);
                Glide.with(MutableActivity.this).load(imageUri).centerCrop().into(image);
                imageUrl = imageResponse.getImage();
                Log.d(TAG, "onChanged: "+ imageUrl);
            }
        });
    }

    private void requestPermission() {
        if(ContextCompat
        .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
        }
        if(ContextCompat
        .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
        }
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "You just denied the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void initUI() {
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        image = findViewById(R.id.image);
        progressBar = findViewById(R.id.progressBar);
        btnSave = findViewById(R.id.save);
    }
}