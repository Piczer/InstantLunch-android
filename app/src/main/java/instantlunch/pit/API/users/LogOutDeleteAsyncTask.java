package instantlunch.pit.API.users;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import instantlunch.pit.API.ServerConnector;
import instantlunch.pit.models.AppUser;

/**
 * Created by Piotr on 2016-08-29.
 */
public class LogOutDeleteAsyncTask extends AsyncTask<String,String,Boolean> {

    private ServerConnector serverConnector = ServerConnector.getInstance();

    private ProgressDialog pDialog;

    private AppCompatActivity activity;

    AppUser appUser = AppUser.getInstance();


    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }
    public LogOutDeleteAsyncTask(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String userToken = params[0];
        return serverConnector.getJsonParser().makeHttpDeleteAuthRequest(serverConnector.getLOGOUT(),userToken);
    }
}
