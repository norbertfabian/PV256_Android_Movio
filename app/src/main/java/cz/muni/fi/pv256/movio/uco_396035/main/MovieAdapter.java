package cz.muni.fi.pv256.movio.uco_396035.main;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.muni.fi.pv256.movio.uco_396035.R;
import cz.muni.fi.pv256.movio.uco_396035.data.Movie;
import cz.muni.fi.pv256.movio.uco_396035.helper.DateConvertor;

/**
 * Created by fabian on 21.10.2017.
 */

public class MovieAdapter extends BaseAdapter {

    private Context mAppContext;
    private ArrayList<Movie> mMovieList;

    public MovieAdapter(Context context, ArrayList<Movie> mMovieList) {
        this.mAppContext = context.getApplicationContext();
        this.mMovieList = mMovieList;
    }

    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(mAppContext).inflate(R.layout.list_item_movie, parent, false);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.bindView(mAppContext, mMovieList.get(position));
        return convertView;
    }

    public static class ViewHolder {
        private ImageView coverIv;
        private TextView releaseDateTv;
        private TextView titleTv;

        public ViewHolder(View view) {
            this.coverIv = view.findViewById(R.id.list_item_icon);
            this.releaseDateTv = view.findViewById(R.id.list_item_date);
            this.titleTv = view.findViewById(R.id.list_item_text);
        }

        public void bindView(Context context, Movie movie) {
            if(movie == null) {
                return;
            }

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
