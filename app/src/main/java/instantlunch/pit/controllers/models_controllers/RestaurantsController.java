package instantlunch.pit.controllers.models_controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import instantlunch.pit.models.Restaurant;

/**
 * Created by Piotr on 2016-06-26.
 */
public class RestaurantsController {

    public List<Restaurant> getArrayFromJSON(JSONObject jsonObject) {
        List<Restaurant> restaurants = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = jsonObject.getJSONArray("restaurants");
            for (int i = 0; i < jsonArray.length(); i++) {
                restaurants.add(new Restaurant(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

}
