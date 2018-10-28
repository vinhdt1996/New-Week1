package com.example.vinhtruong.new_week1.mapper;


import com.example.vinhtruong.new_week1.api.APILink;
import com.example.vinhtruong.new_week1.dto.MovieListingDTO;
import com.example.vinhtruong.new_week1.dto.TrailerListingDTO;
import com.example.vinhtruong.new_week1.mapper.model.Movie;
import com.example.vinhtruong.new_week1.mapper.model.Trailer;

import java.util.ArrayList;
import java.util.List;

public class DTOModelEntitiesDataMapper {
    /**
     * Transform a movie list details movieList entity to a business details model
     *
     * @param dto
     * @return
     */
    public Movie transform(MovieListingDTO.MovieListDTO dto) {
        return new Movie(dto.getId(),
                dto.getTitle(),
                dto.getOriginalTitle(),
                dto.getReleaseDate(),
                createPosterLink(dto.getPosterPath()),
                createPosterLink(dto.getBackdropPath()),
                dto.getVoteAverage(),
                dto.getPopularity(),
                dto.getOverview(),
                dto.getVoteAverage()
        );
    }
    /**
     * Transform a trailer list details trailerList entity to a business details model
     *
     * @param dto
     * @return
     */
    public Trailer transform(TrailerListingDTO.TrailerListDTO dto) {
        return new Trailer(dto.getName(),
                dto.getSize(),
                dto.getSource(),
                dto.getSize()
        );
    }

    /**
     * Transform a movie list movieList entity to a business list model
     *
     * @param dto
     * @return
     */
    public List<Movie> transform(MovieListingDTO dto) {
        List<Movie> movies = new ArrayList<>();
        List<MovieListingDTO.MovieListDTO> dtoList = dto.getResults();
        for (MovieListingDTO.MovieListDTO movie : dtoList) {
            movies.add(transform(movie));
        }
        return movies;
    }
    /**
     * Transform a trailer list movieList entity to a business list model
     *
     * @param dto
     * @return
     */
    public List<Trailer> transform(TrailerListingDTO dto){
        List<Trailer> trailers = new ArrayList<>();
        List<TrailerListingDTO.TrailerListDTO> dtoList = dto.getYoutube();
        for(TrailerListingDTO.TrailerListDTO trailer : dtoList){
            trailers.add(transform(trailer));
        }
        return trailers;
    }

    /**
     * Transform a relative path to a complete URI poster image
     *
     * @param path
     * @return
     */
    private String createPosterLink(String path) {
        if (path == null) return null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(APILink.BASE_IMAGES_URL);
        stringBuilder.append(APILink.POSTER_SIZE);
        stringBuilder.append(path);
        return stringBuilder.toString();
    }


}