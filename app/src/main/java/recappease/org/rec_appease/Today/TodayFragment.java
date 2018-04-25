package recappease.org.rec_appease.Today;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import recappease.org.rec_appease.Login.LoginActivity;
import recappease.org.rec_appease.MainActivity;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Recipes.Recipe;
import recappease.org.rec_appease.Util.BottomNavigationViewHelper;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.R;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class TodayFragment extends Fragment {
    public static final int ACTIVITY_NUM = 4;

    private TextView signout;
    //private Session session;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_today, container, false);

        signout = (TextView) view.findViewById(R.id.signout);

        /*
        BottomNavigationView bottomNavigationView = (BottomNavigationView)  findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
        */

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today();
            }
        });
        return view;

    }

    private void logout() {
        //finish();
        Intent logout = new Intent(TodayFragment.this.getActivity(), LoginActivity.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logout);
        //startActivity(new Intent(TodayFragment.this.getActivity(), LoginActivity.class), FLAG_ACTIVITY_CLEAR_TOP);
    }

    private void today() {

        //Recipe recipe = MainActivity.readRecipe();
        //signout.setText(recipe.title);
    }


}