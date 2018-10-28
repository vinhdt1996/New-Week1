package com.example.vinhtruong.new_week1.ui;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinhtruong.new_week1.R;
import com.example.vinhtruong.new_week1.api.APILink;
import com.example.vinhtruong.new_week1.api.APIService;
import com.example.vinhtruong.new_week1.dto.TrailerListingDTO;
import com.example.vinhtruong.new_week1.mapper.DTOModelEntitiesDataMapper;
import com.example.vinhtruong.new_week1.mapper.model.Trailer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsMovieActivity extends YouTubeBaseActivity {
    TextView textView_Title, textView_ReleaseDay, textView_overview;
    RatingBar ratingBar;
    int id;
    List<Trailer> trailerList;
    Double rating;
    DTOModelEntitiesDataMapper mapper;
    YouTubePlayer player;
    YouTubePlayerView youTubePlayerView;
    private static final String YOUTUBE_API_KEY = "AIzaSyBm_Xmwd6M7L8n8pgRXLgEY2QnN4xmOC1c";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        youTubePlayerView = findViewById(R.id.player);

        trailerList = new ArrayList<>();
        mapper = new DTOModelEntitiesDataMapper();

        textView_Title = findViewById(R.id.tvTitle);
        textView_overview = findViewById(R.id.tvOverview);
        textView_ReleaseDay = findViewById(R.id.tvReleaseDate);
        ratingBar = findViewById(R.id.ratingBar);

//        Glide.with(this).load(movie.getPoster())
//                .into(holder.poster);

        Bundle bundle = getIntent().getExtras();
        rating = bundle.getDouble("Rating");
        id = bundle.getInt("Id",-1);
        textView_Title.setText(bundle.getString("Title"));
        textView_ReleaseDay.setText(bundle.getString("ReleaseDay"));
        ratingBar.setRating((float)bundle.getDouble("Rating"));
        textView_overview.setText(bundle.getString("Overview"));

        callAPI(id);

    }

    private void callAPI(int id) {
        APIService.getInstance().getTrailer(id,APILink.API_KEY, Locale.getDefault().getLanguage()).enqueue(
                new Callback<TrailerListingDTO>() {
                    @Override
                    public void onResponse(Call<TrailerListingDTO> call, Response<TrailerListingDTO> response) {
                        if(response.body() != null){
                             trailerList = mapper.transform(response.body());
                             if(trailerList.size() > 0){
                                 playTrailer(trailerList.get(0).getSource());
                             }
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerListingDTO> call, Throwable t) {

                    }
                }
        );
    }

    private void playTrailer(final String src) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {


                        player = youTubePlayer;
                        // do any work here to cue video, play video, etc.
                        if(player != null && !isFinishing()){
                            if(rating >= 5.0){
                                player.loadVideo(src);
                            }
                            else
                                player.cueVideo(src);
                        }
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            player.setFullscreen(true);
        }
    }
}