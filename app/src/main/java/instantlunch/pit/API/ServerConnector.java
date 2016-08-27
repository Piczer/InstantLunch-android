package instantlunch.pit.API;

public class ServerConnector {

    private static ServerConnector instance;
    private JSONParser jsonParser = new JSONParser();
    private String HOST_IP = "https://instantlunch.herokuapp.com/api/";
    private String LOGIN = HOST_IP + "sessions/login";
    private String LOGOUT = HOST_IP + "sessions/logout";
    private String REGISTER = HOST_IP + "customers";
    private String RESTAURANTS = HOST_IP + "restaurants";
    private String DISH_CATEGORIES = HOST_IP + "dish_categories";
    private String SHOW_FAV_RESTAURANTS = HOST_IP + "restaurants/favorite";
    private String ORDERS = HOST_IP + "orders";
    private String ADD_FAV_RESTAURANT = HOST_IP + "favorite_restaurants";
    private String FAV_RESTAURANTS_PROMOTIONS = HOST_IP + "favorite_restaurants/promotions";
    private String MENU = HOST_IP + "menus/restaurant_menu";

    public static ServerConnector getInstance() {
        if (instance == null){
            instance = new ServerConnector();
        }
        return instance;
    }

    public ServerConnector() {
    }

    public JSONParser getJsonParser() {
        return jsonParser;
    }

    public String getHOST_IP() {
        return HOST_IP;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getLOGOUT() {
        return LOGOUT;
    }

    public String getREGISTER() {
        return REGISTER;
    }

    public String getRESTAURANTS() {
        return RESTAURANTS;
    }

    public String getDISH_CATEGORIES() {
        return DISH_CATEGORIES;
    }

    public String getSHOW_FAV_RESTAURANTS() {
        return SHOW_FAV_RESTAURANTS;
    }

    public String getORDERS() {
        return ORDERS;
    }

    public String getADD_FAV_RESTAURANT() {
        return ADD_FAV_RESTAURANT;
    }

    public String getFAV_RESTAURANTS_PROMOTIONS() {
        return FAV_RESTAURANTS_PROMOTIONS;
    }

    public String getMENU() {
        return MENU;
    }
}
