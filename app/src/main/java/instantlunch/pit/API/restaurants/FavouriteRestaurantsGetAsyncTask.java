package instantlunch.pit.API.restaurants;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import instantlunch.pit.API.ServerConnector;
import instantlunch.pit.adapters.RestaurantsAdapter;
import instantlunch.pit.controllers.models_controllers.RestaurantsController;
import instantlunch.pit.models.AppUser;
import instantlunch.pit.models.Restaurant;

/**
 * Created by Piotr on 2016-06-29.
 */
public class FavouriteRestaurantsGetAsyncTask extends AsyncTask<String,String,JSONObject> {
    private ServerConnector serverConnector = ServerConnector.getInstance();

    private ProgressDialog pDialog;
    private AppUser user;
    private AppCompatActivity activity;
    private RecyclerView favRestaurantsRecyclerView;
    private RestaurantsAdapter favRestaurantsAdapter;

    public FavouriteRestaurantsGetAsyncTask(AppCompatActivity activity, RecyclerView favRestaurantsRecyclerView)
    {
        this.activity = activity;
        this.favRestaurantsRecyclerView = favRestaurantsRecyclerView;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setRestaurantsListView(RecyclerView favRestaurantsRecyclerView)
    {
        this.favRestaurantsRecyclerView = favRestaurantsRecyclerView;
    }
    @Override
    protected void onPreExecute() {
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Trwa pobieranie restauracji");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected JSONObject doInBackground( String... args) {
        try {
            HashMap<String, String> params = new HashMap<>();
            JSONObject result = serverConnector.getJsonParser().makeHttpGetAuthRequest(serverConnector.getSHOW_FAV_RESTAURANTS(),user.getInstance().getUser().getToken());
            if(result != null)
            {
                Log.d("Restaurants JSON result: ", result.toString());
                return result;
            }

        } catch (Exception e) {
            Log.d("HttpGet", e.getLocalizedMessage());
        }
        return null;
    }

    protected void onPostExecute(JSONObject result) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }

        try
        {
            List<Restaurant> restaurants = new RestaurantsController().getArrayFromJSON(result);
            favRestaurantsAdapter = new RestaurantsAdapter(restaurants);
            favRestaurantsRecyclerView.setAdapter(favRestaurantsAdapter);
            final LinearLayoutManager mLayoutManager;
            mLayoutManager = new LinearLayoutManager(activity);
            favRestaurantsRecyclerView.setLayoutManager(mLayoutManager);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
