package com.example.arjun.poczomato;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arjun.poczomato.View.category.CategoryFragment;
import com.example.arjun.poczomato.View.HomeFragment;
import com.example.arjun.poczomato.View.SearchFragment;

public class MainActivity extends AppCompatActivity implements ActiviyListener
{
    //8b4934620d60843ab88a5ed9f6199ab0
    private static final String TAG = "Category";
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBar actionbar;
    Toolbar toolbar;
    ImageView iv_home, iv_back;
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_home = findViewById(R.id.home_icon);
        iv_back = findViewById(R.id.back_icon);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);

                return true;

            }


        });
        // disable appname
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // select home as default
        navigationView.getMenu().getItem(0).setChecked(true);
        pushfragment(HomeFragment.getInstance(), false);
        iv_home.setVisibility(View.VISIBLE);


    }

    private void selectDrawerItem(MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                fragment = HomeFragment.getInstance();
                pushfragment(fragment, false);
                menuItem.setChecked(true);
                break;
            case R.id.menu_category:

                fragment = CategoryFragment.getnstance();
                pushfragment(fragment, true);
                menuItem.setChecked(true);
                break;
            case R.id.menu_search:

                fragment = SearchFragment.getInstance();
                pushfragment(fragment, true);
                menuItem.setChecked(true);
                break;
        }


        // Highlight the selected item has been done by NavigationView

        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();

    }


    public void pushfragment(Fragment fragment, boolean addTobackStack) {
        if (addTobackStack) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(fragment.toString()).commit();
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    // not using
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.container);
        if(fragment instanceof HomeFragment){
            Toast.makeText(this, "HomeFragment", Toast.LENGTH_SHORT).show();

        }else if(fragment instanceof CategoryFragment){
            Toast.makeText(this, "CategoryFragment", Toast.LENGTH_SHORT).show();

        }else  if(fragment instanceof SearchFragment) {
            Toast.makeText(this, "SearchFragment", Toast.LENGTH_SHORT).show();
        }

        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }



    @Override
    public void settitle(boolean menuicon, String title) {
        toolbar_title.setText(title);
        if(menuicon) {
            iv_home.setVisibility(View.VISIBLE);
            iv_back.setVisibility(View.GONE);
        }
        else {
            iv_back.setVisibility(View.VISIBLE);
            iv_home.setVisibility(View.GONE);

        }
    }

    @Override
    public void addfragment(Fragment fragment) {
        pushfragment(fragment,true);

    }
}
