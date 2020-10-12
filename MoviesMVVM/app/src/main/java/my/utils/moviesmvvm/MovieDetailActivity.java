package my.utils.moviesmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import my.utils.moviesmvvm.model.Result;


public class MovieDetailActivity extends AppCompatActivity {
    private Result result;
    private ImageView imageView;
    String posterPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("movie")) {
            result = intent.getParcelableExtra("movie");

            String imagePath = "http://image.tmdb.org/t/p/w500" + result.getPosterPath();
            Glide.with(this)
                    .load(imagePath)
                    .placeholder(R.drawable.progress_circle)
                    .into(imageView);

        }
    }
}