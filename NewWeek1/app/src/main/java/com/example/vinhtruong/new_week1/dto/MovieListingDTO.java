package com.example.vinhtruong.new_week1.dto;

import java.util.List;

public class MovieListingDTO {

    private List<MovieListDTO> results;

    public List<MovieListDTO> getResults() {
        return results;
    }

    /**
     * Small Movie Data Transfer Object
     * Used to store a movie list retrieved from a list of movies from TMDb
     */
    public static class MovieListDTO {

        private int id;
        private String title;
        private String poster_path;
        private String backdrop_path;
        private String release_date;
        private double vote_average;
        private double popularity;
        private String original_title;
        private String overview;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getPosterPath() {
            return poster_path;
        }

        public String getBackdropPath() {
            return backdrop_path;
        }

        public String getReleaseDate() {
            return release_date;
        }

        public double getVoteAverage() {
            return vote_average;
        }

        public String getOriginalTitle() {
            return original_title;
        }

        public String getOverview() {
            return overview;
        }

        public double getPopularity() {
            return popularity;
        }
    }
}
