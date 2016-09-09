package instantlunch.pit.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import instantlunch.pit.API.users.LogOutDeleteAsyncTask;
import instantlunch.pit.R;
import instantlunch.pit.models.AppUser;
import instantlunch.pit.views.FavouriteRestaurantsActivity;
import instantlunch.pit.views.LoginActivity;
import instantlunch.pit.views.RestaurantsActivity;
import instantlunch.pit.views.ScanCodeActivity;

/**
 * Created by impresyjna on 09.05.16.
 */
public class NavigationController extends AppCompatActivity{

    private AppUser appUser = AppUser.getInstance();

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
            logUserOut(activity);
        }
    }

    public void openIntent(Activity activity, Class windowClass) {
        Intent intent = new Intent(activity, windowClass);
        activity.startActivity(intent);
        activity.finish();
    }

    public void logUserOut(Activity activity){
        try{
            LogOutDeleteAsyncTask logoutTask = new LogOutDeleteAsyncTask(this);
            logoutTask.execute(appUser.getUser().getToken());
            appUser.setUser(null);
            openIntent(activity, LoginActivity.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
