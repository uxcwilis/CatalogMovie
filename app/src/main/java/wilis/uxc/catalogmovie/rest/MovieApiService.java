package wilis.uxc.catalogmovie.rest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;
import wilis.uxc.catalogmovie.model.MovieResponse;
import wilis.uxc.catalogmovie.model.SearchMovieResponse;


public interface MovieApiService {

     @GET("movie/top_rated")
     Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<SearchMovieResponse> getSearchMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);


}







