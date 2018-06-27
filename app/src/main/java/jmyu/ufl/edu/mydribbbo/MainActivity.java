package jmyu.ufl.edu.mydribbbo;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import jmyu.ufl.edu.mydribbbo.view.bucket_list.BucketListFragment;
import jmyu.ufl.edu.mydribbbo.view.shot_list.ShotListFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        // set item as selected to persist highlight
                        item.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        Fragment fragment = null;
                        switch (item.getItemId()) {
                            case R.id.drawer_item_home:
                                fragment = ShotListFragment.newInstance();
                                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();

                                setTitle(R.string.title_home);
                                break;
                            case R.id.drawer_item_likes:
                                fragment = ShotListFragment.newInstance();
                                Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();

                                setTitle(R.string.title_likes);
                                break;
                            case R.id.drawer_item_buckets:
                                fragment = BucketListFragment.newInstance();
                                Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();

                                setTitle(R.string.title_buckets);
                                break;
                        }

                        if (fragment != null) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();
                            return true;
                        }

                        return false;
                    }
                });
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_frame, ShotListFragment.newInstance())
                    .commit();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
