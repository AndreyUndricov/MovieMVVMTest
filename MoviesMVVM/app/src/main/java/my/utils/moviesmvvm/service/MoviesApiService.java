package my.utils.moviesmvvm.service;

import java.util.List;

import my.utils.moviesmvvm.model.MovieApiResponse;
import my.utils.moviesmvvm.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApiService {

    @GET("movie/popular")
    Call<MovieApiResponse> getPopularMovies(@Query("api_key") String apiKey);
}
