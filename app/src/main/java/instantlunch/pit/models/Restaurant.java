package instantlunch.pit.models;


import org.json.JSONException;
import org.json.JSONObject;

public class Restaurant {
    public int id;
    public String name;
    public String street;
    public String house_number;
    public String apartment_number;
    public String post_code;
    public String city;

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_STREET = "street";
    private static final String TAG_HOUSE_NUM = "house_number";
    private static final String TAG_APARTMENT_NUM = "apartment_number";
    private static final String TAG_POSTCODE = "post_code";
    private static final String TAG_CITY = "city";

    public Restaurant(JSONObject json)
    {
        try {
            this.id = json.getInt(TAG_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.name = json.getString(TAG_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.street = json.getString(TAG_STREET);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.house_number = json.getString(TAG_HOUSE_NUM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.apartment_number = json.getString(TAG_APARTMENT_NUM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.post_code = json.getString(TAG_POSTCODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.city = json.getString(TAG_CITY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
