package cz.muni.fi.pv256.movio.uco_396035.main;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cz.muni.fi.pv256.movio.uco_396035.R;
import cz.muni.fi.pv256.movio.uco_396035.data.Movie;
import cz.muni.fi.pv256.movio.uco_396035.helper.DateConvertor;

/**
 * Created by fabian on 21.10.2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mAppContext;
    private ArrayList<Movie> mMovieList;
    private OnMovieClickListener clickListener;
    private OnMovieLongClickListener longClickListener;

    public MovieAdapter(Context context, ArrayList<Movie> mMovieList,
                        OnMovieClickListener clickListener,
                        OnMovieLongClickListener longClickListener) {
        this.mAppContext = context.getApplicationContext();
        this.mMovieList = mMovieList;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mAppContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(mAppContext, mMovieList.get(position), clickListener, longClickListener);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public interface OnMovieLongClickListener {
        boolean onMovieLongClick(Movie movie, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView coverIv;
        private TextView releaseDateTv;
        private TextView titleTv;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.coverIv = view.findViewById(R.id.list_item_icon);
            this.releaseDateTv = view.findViewById(R.id.list_item_date);
            this.titleTv = view.findViewById(R.id.list_item_text);
        }

        public void bindView(Context context, Movie movie,
                             OnMovieClickListener movieClickListener,
                             OnMovieLongClickListener movieLongClickListener) {

            if(movie == null) {
                return;
            }

            view.setOnClickListener(v1 -> movieClickListener.onMovieClick(movie));
            view.setOnLongClickListener(
                    v2 -> movieLongClickListener.onMovieLongClick(movie, this.getAdapterPosition()));
            titleTv.setText(movie.getTitle());
            releaseDateTv.setText(DateConvertor.longDateToString(movie.getReleaseDate()));

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                coverIv.setImageDrawable(context.getDrawable(movie.getCoverId()));
            } else {
                coverIv.setImageDrawable(context.getResources().getDrawable(movie.getCoverId()));
            }
            coverIv.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}
