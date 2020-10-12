package my.utils.moviesmvvm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import my.utils.moviesmvvm.MovieDetailActivity;
import my.utils.moviesmvvm.R;
import my.utils.moviesmvvm.activitys.MainActivity;
import my.utils.moviesmvvm.model.Result;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Result> arrayList;
    private Context context;

    public Adapter(ArrayList<Result> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.result_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = arrayList.get(position);
        holder.titleTextView.setText(result.getOriginalTitle());
        holder.popularTextView.setText(Double.toString(result.getPopularity()));
        String imagePath = "http://image.tmdb.org/t/p/w500" + result.getPosterPath();
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.progress_circle)
                .into(holder.moviewImageView);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView popularTextView;
        ImageView moviewImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            popularTextView = itemView.findViewById(R.id.popularityTextView);
            moviewImageView = itemView.findViewById(R.id.movieImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION) {
                        Result result = arrayList.get(position);
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra("movie",result);
                        context.startActivity(intent);


                    }
                }
            });
        }
    }
}
