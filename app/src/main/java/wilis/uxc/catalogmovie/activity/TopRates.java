package wilis.uxc.catalogmovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import wilis.uxc.catalogmovie.R;
import wilis.uxc.catalogmovie.adapter.MoviesAdapter;
import wilis.uxc.catalogmovie.model.Movie;
import wilis.uxc.catalogmovie.model.MovieResponse;
import wilis.uxc.catalogmovie.rest.MovieApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TopRates extends AppCompatActivity {

    private static final String TAG = TopRates.class.getSimpleName();
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "a827a8423ef97c3a2f78d83a30b72ce9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toprates);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectAndGetApiData();

    }


    // This method create an instance of Retrofit
    // set the base url
    public void connectAndGetApiData(){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<MovieResponse> call = movieApiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                final List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                Log.d(TAG, "Number of movies received: " + movies.size());
                Toast.makeText(TopRates.this, "Number of movies received: " + movies.size(), Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));

                /*perintah klik recyclerview*/
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                        public boolean onSingleTapUp(MotionEvent e){
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)){
                            int position = rv.getChildAdapterPosition(child);
                            Toast.makeText(getApplicationContext(), "Id : " + movies.get(position).getId() + " selected", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), DescriptionActivity.class);
                            i.putExtra("id", movies.get(position).getId());
                            i.putExtra("title", movies.get(position).getTitle());
                            i.putExtra("date", movies.get(position).getReleaseDate());
                            i.putExtra("vote", movies.get(position).getVoteAverage().toString());
                            i.putExtra("overview", movies.get(position).getOverview());
                            i.putExtra("bg", movies.get(position).getPosterPath());
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });
            }


            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}