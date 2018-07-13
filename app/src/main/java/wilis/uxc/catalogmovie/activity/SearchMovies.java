package wilis.uxc.catalogmovie.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wilis.uxc.catalogmovie.R;
import wilis.uxc.catalogmovie.adapter.MoviesAdapter;
import wilis.uxc.catalogmovie.model.Movie;
import wilis.uxc.catalogmovie.model.MovieResponse;
import wilis.uxc.catalogmovie.model.SearchMovieResponse;
import wilis.uxc.catalogmovie.rest.MovieApiService;


public class SearchMovies extends AppCompatActivity{

    private static final String TAG = SearchMovies.class.getSimpleName();
    private final static String API_KEY = "a827a8423ef97c3a2f78d83a30b72ce9";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    EditText txt_cari;
    Button btn_cari;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchmovie);

        txt_cari = (EditText) findViewById(R.id.txt_cari);
        btn_cari = (Button) findViewById(R.id.btn_cari);

        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectAndGetApiData(String.valueOf(txt_cari.getText()));
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        connectAndGetApiData();
    }



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
                            Intent i = new Intent(SearchMovies.this, DescriptionActivity.class);
                            i.putExtra("id", movies.get(position).getId());
                            i.putExtra("title", movies.get(position).getTitle());
                            i.putExtra("date", movies.get(position).getReleaseDate());
                            i.putExtra("vote", movies.get(position).getVoteAverage().toString());
                            i.putExtra("overview", movies.get(position).getOverview());
                            i.putExtra("bg", movies.get(position).getPosterPath());
                            startActivity(i);
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

    public void connectAndGetApiData(String query){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        Call<SearchMovieResponse> call = movieApiService.getSearchMovies(API_KEY, query);
        call.enqueue(new Callback<SearchMovieResponse>() {
            @Override
            public void onResponse(Call<SearchMovieResponse> call, Response<SearchMovieResponse> response) {
                final List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                Log.d(TAG, "Number of movies received: " + movies.size());

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
                            Intent i = new Intent(SearchMovies.this, DescriptionActivity.class);
                            i.putExtra("id", movies.get(position).getId());
                            i.putExtra("title", movies.get(position).getTitle());
                            i.putExtra("date", movies.get(position).getReleaseDate());
                            i.putExtra("vote", movies.get(position).getVoteAverage().toString());
                            i.putExtra("overview", movies.get(position).getOverview());
                            i.putExtra("bg", movies.get(position).getPosterPath());
                            startActivity(i);
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
            public void onFailure(Call<SearchMovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
