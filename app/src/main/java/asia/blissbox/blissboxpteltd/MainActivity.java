package asia.blissbox.blissboxpteltd;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;
    FragmentManager fm;
    FragmentTransaction trans;
    ArrayList<ObjectBoxes> allBoxes;
    ObjectBoxes feature1, feature2, feature3, feature4, feature5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomBar = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomBar);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Quicksand-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        if (savedInstanceState == null) {
            Fragment fragmentHome = new FragmentHome();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment, fragmentHome);
            fragmentTransaction.commit();
        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new FragmentHome();
                    fm = getSupportFragmentManager();
                    trans = fm.beginTransaction();
                    trans.replace(R.id.fragment, fragment);
                    trans.commit();

                    return true;

                case R.id.navigation_location:
                    fragment = new FragmentExperiences();
                    fm = getSupportFragmentManager();
                    trans = fm.beginTransaction();
                    trans.replace(R.id.fragment, fragment);
                    trans.commit();

                    return true;

                case R.id.navigation_shopping_cart:
                    fragment = new FragmentCart();
                    fm = getSupportFragmentManager();
                    trans = fm.beginTransaction();
                    trans.replace(R.id.fragment, fragment);
                    trans.commit();

                    return true;

                case R.id.navigation_user:
                    fragment = new FragmentLoginSignup();
                    fm = getSupportFragmentManager();
                    trans = fm.beginTransaction();
                    trans.replace(R.id.fragment, fragment);
                    trans.commit();

                    return true;
            }
            return false;
        }
    };


}