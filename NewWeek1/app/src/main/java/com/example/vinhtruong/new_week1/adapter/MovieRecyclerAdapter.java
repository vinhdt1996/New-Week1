package com.example.vinhtruong.new_week1.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vinhtruong.new_week1.mapper.model.Movie;
import com.example.vinhtruong.new_week1.R;

import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private List<Movie> data;
    private Context context;
    private IClickListener listener;
    Movie movie;

    /**
     * Constructs a new MovieRecyclerAdapter with a context
     *
     * @param ctx
     */
    public MovieRecyclerAdapter(Context ctx) {
        this.context = ctx;
    }

    /**
     * Replace movieList in the adapter
     *
     * @param data
     */
    public void setData(List<Movie> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * Sets the click listener
     * @param listener
     */
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    /**
     * Clear all movieList from adapter
     */
    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movies_row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        movie = data.get(position);

        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());
        holder.releaseDay = movie.getReleaseDate();
        holder.vote_average = movie.getVote_average();

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if (holder.vote_average > 5.0){
                holder.title.setVisibility(View.GONE);
                holder.overview.setVisibility(View.GONE);
                holder.poster.setVisibility(View.GONE);
                holder.backdrop_fullLayout.setVisibility(View.VISIBLE);
                Glide.with(context).load(movie.getBackdrop())
                        .into(holder.backdrop_fullLayout);

            }
            else{
                holder.title.setVisibility(View.VISIBLE);
                holder.overview.setVisibility(View.VISIBLE);
                holder.poster.setVisibility(View.VISIBLE);
                holder.backdrop_fullLayout.setVisibility(View.GONE);
                Glide.with(context).load(movie.getPoster())
                        .into(holder.poster);
            }

        }
        else {
            if (holder.vote_average > 5.0){
                holder.title.setVisibility(View.GONE);
                holder.overview.setVisibility(View.GONE);
                holder.poster.setVisibility(View.GONE);
                holder.backdrop_fullLayout.setVisibility(View.VISIBLE);
                Glide.with(context).load(movie.getBackdrop())
                        .into(holder.backdrop_fullLayout);
            }
            else {
                holder.title.setVisibility(View.VISIBLE);
                holder.overview.setVisibility(View.VISIBLE);
                holder.poster.setVisibility(View.VISIBLE);
                holder.backdrop_fullLayout.setVisibility(View.GONE);
                Glide.with(context).load(movie.getBackdrop())
                        .into(holder.poster);
            }
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Interface for click listeners for this adapter
     */
    public interface IClickListener {
        void onItemClick(Movie movie);
    }

    /**
     * List row members
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView overview;
        public ImageView poster, backdrop_fullLayout;

        public String releaseDay;
        public double vote_average;


        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title_details);
            overview = itemView.findViewById(R.id.movie_overview);
            poster = itemView.findViewById(R.id.poster);
            backdrop_fullLayout = itemView.findViewById(R.id.backdrop_fullLayout);
        }
    }


}