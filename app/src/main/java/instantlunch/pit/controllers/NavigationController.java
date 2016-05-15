package instantlunch.pit.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import instantlunch.pit.R;
import instantlunch.pit.views.FavouriteRestaurantsActivity;
import instantlunch.pit.views.RestaurantsActivity;
import instantlunch.pit.views.ScanCodeActivity;

/**
 * Created by impresyjna on 09.05.16.
 */
public class NavigationController extends AppCompatActivity{

    public void itemSelectedOperation(MenuItem item, Activity activity) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scan) {
            openIntent(activity, ScanCodeActivity.class);
        } else if (id == R.id.nav_favourite) {
            openIntent(activity, FavouriteRestaurantsActivity.class);
        } else if (id == R.id.nav_restaurants) {
            openIntent(activity, RestaurantsActivity.class);
        }  else if (id == R.id.nav_logout) {

        }
    }

    public void openIntent(Activity activity, Class windowClass) {
        Intent intent = new Intent(activity, windowClass);
        activity.startActivity(intent);
        activity.finish();
    }
}
