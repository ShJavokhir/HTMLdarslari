package uz.dasturlash.html;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class User_Dashboard extends AppCompatActivity {
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle draweToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_dashboard);

        init();

        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        draweToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(draweToggle);
        draweToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void init(){
        navigationView = (NavigationView) findViewById(R.id.navigation_view1);
        initDrawerMenu();
        toolbar = (Toolbar) findViewById(R.id.toolbar5);
    }
    private void initDrawerMenu(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                Intent intent;
                switch (id) {
                    case R.id.dashboard:
                        //intent = new Intent(User_Dashboard.this, User_Dashboard.class);
                        //startActivity(intent);
                        break;
                    case R.id.about_app:
                        intent = new Intent(User_Dashboard.this, AboutApp.class);
                        startActivity(intent);
                }

                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(draweToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
