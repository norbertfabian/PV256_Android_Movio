package uco_396035.movio2.pv256.fi.muni.cz.movio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeTheme();
        setContentView(R.layout.activity_main);
    }

    private void changeTheme() {
        if (getTheme().equals(R.style.AppTheme)) {
            setTheme(R.style.SecondaryAppTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }
}
