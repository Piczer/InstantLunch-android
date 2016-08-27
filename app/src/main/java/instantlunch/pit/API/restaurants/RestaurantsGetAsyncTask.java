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
import instantlunch.pit.models.Restaurant;

public class RestaurantsGetAsyncTask extends AsyncTask<String,String,JSONObject> {
    private ServerConnector serverConnector = ServerConnector.getInstance();

    private ProgressDialog pDialog;

    private AppCompatActivity activity;
    private RecyclerView restaurantsRecyclerView;
    private RestaurantsAdapter restaurantsAdapter;

    public RestaurantsGetAsyncTask(AppCompatActivity activity, RecyclerView restaurantsRecyclerView)
    {
        this.activity = activity;
        this.restaurantsRecyclerView = restaurantsRecyclerView;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setRestaurantsListView(RecyclerView restaurantsRecyclerView)
    {
        this.restaurantsRecyclerView = restaurantsRecyclerView;
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
            JSONObject result = serverConnector.getJsonParser().makeHttpRequest(serverConnector.getRESTAURANTS(),"GET",params);
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
            restaurantsAdapter = new RestaurantsAdapter(restaurants);
            restaurantsRecyclerView.setAdapter(restaurantsAdapter);
            final LinearLayoutManager mLayoutManager;
            mLayoutManager = new LinearLayoutManager(activity);
            restaurantsRecyclerView.setLayoutManager(mLayoutManager);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
