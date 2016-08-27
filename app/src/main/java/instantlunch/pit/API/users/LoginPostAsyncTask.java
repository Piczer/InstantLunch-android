package instantlunch.pit.API.users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import instantlunch.pit.API.ServerConnector;
import instantlunch.pit.models.AppUser;
import instantlunch.pit.models.User;
import instantlunch.pit.views.LoginActivity;
import instantlunch.pit.views.MainActivity;

/**
 * Created by Piotr on 2016-06-27.
 */
public class LoginPostAsyncTask extends AsyncTask<String, String, JSONObject> {

    private ServerConnector serverConnector = ServerConnector.getInstance();

    private ProgressDialog pDialog;

    private AppCompatActivity activity;

    AppUser appUser = AppUser.getInstance();


    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }
    public LoginPostAsyncTask(AppCompatActivity activity) {
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Trwa logowanie");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
    @Override
    protected JSONObject doInBackground(String... args) {
        try
        {
            JSONObject jsonItems = new JSONObject();
            jsonItems.put("login", args[0]);
            jsonItems.put("password", args[1]);
            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("session", jsonItems);

            Log.d("request", "starting");

            JSONObject result = serverConnector.getJsonParser().makeHttpPostRequest(serverConnector.getLOGIN(),jsonRequestBody);
            if (result != null) {
                Log.d("JSON result", result.toString());
                return result;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(JSONObject json) {

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
        try {
            if (json.getJSONObject("customer").has("login") && !json.getJSONObject("customer").isNull("login")) {
                User user = null;
                try {
                    user = new User(json.getJSONObject("customer"));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                appUser.setUser(user);
                Log.i("MainPage", "Opening main page activity ");
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            } else if(activity instanceof LoginActivity){
                Toast.makeText(activity, "Niepoprawne dane", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
