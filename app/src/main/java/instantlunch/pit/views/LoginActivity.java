package instantlunch.pit.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import instantlunch.pit.API.users.LoginPostAsyncTask;
import instantlunch.pit.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailInput = (EditText) findViewById(R.id.email_input);
        passwordInput = (EditText) findViewById(R.id.password_input);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        if(!validatePassword())
        {
            return;
        }
        else {
            /* LoginMobile loginMobile = new LoginMobile();
            loginMobile.execute(email, password); */
//            Log.i("About", "Opening about page activity ");
            LoginPostAsyncTask loginTask = new LoginPostAsyncTask(this);
            loginTask.execute(email,password);
//            Intent intent = new Intent(this, MainActivity.class);
//            this.startActivity(intent);
        }
    }

    /**
     * Open About activity
     * @param view
     */
    public void showAbout(View view)
    {
        Log.i("About", "Opening about page activity ");
        Intent intent = new Intent(this, AboutActivity.class);
        this.startActivity(intent);
    }

    /** Open Register activity */
    public void showRegister(View view)
    {
        Log.i("Register", "Opening register page activity");
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    /**
     * Validates if password is not empty and length is appropriate
     * @return
     */
    public boolean validatePassword(){
        String password = passwordInput.getText().toString().trim();
        if (password.isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password_empty));
            requestFocus(emailInput);
            return false;
        } else if(password.length()<3) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password_short));
            requestFocus(emailInput);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
