package com.example.mvvmweekend.view.articleDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mvvmweekend.R;
import com.example.mvvmweekend.data.models.Article;

public class ArticleDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView title, desc;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        initUI();

        Intent intent = getIntent();
        Article article = (Article) intent.getSerializableExtra("article");
        if(article != null){
            title.setText(article.getTitle());
            desc.setText(article.getDescription());
            Glide.with(this).load(article.getImage_url()).into(imageView);
        }else {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
        
    }

    private void initUI() {
        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
    }
}











