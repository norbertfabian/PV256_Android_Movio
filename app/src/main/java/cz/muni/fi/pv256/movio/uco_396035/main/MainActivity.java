package cz.muni.fi.pv256.movio.uco_396035.main;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cz.muni.fi.pv256.movio.uco_396035.R;
import cz.muni.fi.pv256.movio.uco_396035.data.Movie;
import cz.muni.fi.pv256.movio.uco_396035.detail.DetailActivity;
import cz.muni.fi.pv256.movio.uco_396035.detail.DetailFragment;


public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if(findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if(savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new DetailFragment(), DetailFragment.TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }
    }

    @Override
    public void onMovieClick(Movie movie) {
        if(mTwoPane) {
            FragmentManager fm = getSupportFragmentManager();

            DetailFragment fragment = DetailFragment.newInstance(movie);
            fm.beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, DetailFragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
            startActivity(intent);
        }
    }
}
