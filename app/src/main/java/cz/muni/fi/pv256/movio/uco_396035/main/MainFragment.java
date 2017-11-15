package cz.muni.fi.pv256.movio.uco_396035.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cz.muni.fi.pv256.movio.uco_396035.data.Movie;
import cz.muni.fi.pv256.movio.uco_396035.R;
import cz.muni.fi.pv256.movio.uco_396035.data.MovieData;


/**
 * Created by fabian on 19.10.2017.
 */

public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();
    private static final String SELECTED_KEY = "selected_position";

    private int mPosition = ListView.INVALID_POSITION;
    private MovieAdapter.OnMovieClickListener mListener;
    private Context mContext;
    private RecyclerView mListView;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            mListener = (MovieAdapter.OnMovieClickListener) activity;
        } catch (ClassCastException e) {
            Log.e(TAG, "Activity must implement OnMovieClickListener", e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Avoid leaking the Activity
        mListener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity().getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        fillListView(view);

        if(savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            mPosition = savedInstanceState.getInt(SELECTED_KEY);

            if(mPosition != ListView.INVALID_POSITION) {
                mListView.smoothScrollToPosition(mPosition);
            }
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    private void fillListView(View rootView) {
        ArrayList<Movie> movieList = MovieData.getInstance().getMovieList();
        mListView = rootView.findViewById(R.id.listview_movies);
        View mEmptyList = rootView.findViewById(R.id.empty_list);

        boolean emptyView = movieList == null || movieList.isEmpty();
        if(!emptyView) {
            setAdapter(mListView, movieList);
        }
        mListView.setVisibility(emptyView ? View.GONE : View.VISIBLE);
        mEmptyList.setVisibility(emptyView ? View.VISIBLE : View.GONE);
    }

    private void setAdapter(RecyclerView filmRV, final ArrayList<Movie> movieList) {
        MovieAdapter adapter = new MovieAdapter(mContext, movieList, mListener,
                (movie, position) -> {
                    Toast.makeText(mContext, movieList.get(position).getTitle(), Toast.LENGTH_SHORT)
                            .show();
                    return true;
                });

        filmRV.setAdapter(adapter);
        filmRV.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
