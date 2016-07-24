package com.github.handioq.fanshop.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.cart.CartActivity;
import com.github.handioq.fanshop.catalog.CatalogActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseNavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Unbinder unbinder;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ActionBarDrawerToggle mToggle;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        unbinder = ButterKnife.bind(this);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mToggle);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_catalog) {

            Intent intent = new Intent(BaseNavActivity.this, CatalogActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // or FLAG_ACTIVITY_CLEAR_TOP + FLAG_ACTIVITY_NEW_TASK
            startActivity(intent);

        } else if (id == R.id.nav_cart) {

            Intent intent = new Intent(BaseNavActivity.this, CartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // or FLAG_ACTIVITY_CLEAR_TOP + FLAG_ACTIVITY_NEW_TASK
            startActivity(intent);

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_delivery) {
            Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected final NavigationView getNavigationView() {
        return navigationView;
    }

    protected final DrawerLayout getDrawer() {
        return drawer;
    }

    protected final Unbinder getUnbinder() {
        return unbinder;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
