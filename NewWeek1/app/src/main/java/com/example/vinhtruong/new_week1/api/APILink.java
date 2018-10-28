package com.example.vinhtruong.new_week1.api;



import com.example.vinhtruong.new_week1.dto.MovieListingDTO;

import com.example.vinhtruong.new_week1.dto.TrailerListingDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APILink {
    String API_KEY = "51b4a4880d74e49aed8056e0d59b644a";
    String BASE_URL = "http://api.themoviedb.org";
    String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    String POSTER_SIZE = "w185";

//    https://api.themoviedb.org/3/movie/335983/trailers?api_key=51b4a4880d74e49aed8056e0d59b644a
    /**
     * Retrieves a specific movie through his {@code id}
     *
     * @param id      movie id
     * @param api_key api_key used to retrieve information
     * @param lang    to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/{id}/trailers")
    Call<TrailerListingDTO> getTrailer(@Path("id") int id,
                                       @Query("api_key") String api_key,
                                       @Query("language") String lang);

    /**
     * Retrieves a list of movies that are now on cinema.
     *
     * @param API_KEY  api_key used to retrieve information
     * @return
     */
    @GET("/3/movie/now_playing")
    Call<MovieListingDTO> getNowPlaying(@Query("api_key") String API_KEY);

}
