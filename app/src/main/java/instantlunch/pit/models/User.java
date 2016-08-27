package instantlunch.pit.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Piotr on 2016-06-27.
 */
public class User {
    private int id;
    private String login;
    private String password;
    private String passwordConfirmation;
    private String email;
    private String name;
    private String surname;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    private static final String TAG_ID = "id";
    private static final String TAG_LOGIN = "login";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_NAME = "name";
    private static final String TAG_SURNAME = "surname";

    private static final String TAG_TOKEN = "auth_token";

    public User() {
        super();
    }

    public User(JSONObject json) {
        super();
        try {
            this.id = json.getInt(TAG_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.login = json.getString(TAG_LOGIN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.email = json.getString(TAG_EMAIL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.name = json.getString(TAG_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.surname = json.getString(TAG_SURNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.token = json.getString(TAG_TOKEN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
