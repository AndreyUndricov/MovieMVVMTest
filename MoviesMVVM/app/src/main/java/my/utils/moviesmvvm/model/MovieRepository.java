package my.utils.moviesmvvm.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import my.utils.moviesmvvm.R;
import my.utils.moviesmvvm.service.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private Application application;
    private ArrayList<Result> results= new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData(){
        NetworkService.getInstance()
                .getJSONApi()
                .getPopularMovies(application.getString(R.string.apiKey))
                .enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                        MovieApiResponse movieApiResponse = response.body();
                        if (movieApiResponse != null && movieApiResponse.getResults() != null) {
                            results = (ArrayList<Result>) movieApiResponse.getResults();
                            mutableLiveData.setValue(results);
                        }

                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {

                    }
                });
        return  mutableLiveData;
    }
}
