package com.example.samuel.tfgapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FirstPage extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Toolbar myToolbar;
    NavigationView navigationView;

    Intent intent;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        setMyToolbar();
        setDrawerLayout();
    }

    private void setMyToolbar(){
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle("Welcome");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(myToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

    }

    private void setDrawerLayout(){
        mDrawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        // set item as selected to persist highlight
                        item.setChecked(true);
                        //close navigation drawer
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        switch (item.getItemId()) {
                            case R.id.drw_science_technology:
                                intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.drw_maps:
                                intent = new Intent(getApplicationContext(), FemaleResearch.class);
                                startActivity(intent);
                                break;
                            case R.id.drw_customers:
                                toast = Toast.makeText(getApplicationContext(), "Customers", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                            case R.id.drw_reports:
                                toast = Toast.makeText(getApplicationContext(), "Reports", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                            case R.id.drw_integration:
                                toast = Toast.makeText(getApplicationContext(), "Integration", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                        }

                        // set item as selected to persist highlight
                        item.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();


                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_usrOptions:
                Intent intent = new Intent(this, usrOptionsActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_dashboard:
                intent = new Intent(this, WelcomeActivity.class);
                /*EditText editText = (EditText) findViewById(R.id.editText);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);*/
                startActivity(intent);
                return true;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;




            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
