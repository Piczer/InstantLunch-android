package instantlunch.pit.API.users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import instantlunch.pit.API.ServerConnector;
import instantlunch.pit.views.LoginActivity;

/**
 * Created by Piotr on 2016-06-30.
 */
public class RegisterPostAsyncTask extends AsyncTask<String,String,JSONObject> {
    private ServerConnector serverConnector = ServerConnector.getInstance();

    private ProgressDialog pDialog;

    private AppCompatActivity activity;

    public RegisterPostAsyncTask(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Trwa rejestracja...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
    @Override
    protected JSONObject doInBackground(String... args) {
        try{
            JSONObject jsonItems = new JSONObject();
            jsonItems.put("login", args[0]);
            jsonItems.put("password", args[1]);
            jsonItems.put("password_confirmation", args[2]);
            jsonItems.put("name", args[3]);
            jsonItems.put("surname", args[4]);
            jsonItems.put("email", args[5]);
            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("customer", jsonItems);

            JSONObject result = serverConnector.getJsonParser().makeHttpPostRequest(serverConnector.getREGISTER(),jsonRequestBody);
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
            if (json != null && json.getJSONObject("customer").has("login") && !json.getJSONObject("customer").isNull("login")) {
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
            else{
                Toast.makeText(activity, "Nie udało się zarejestrować. Spróbuj ponownie.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
