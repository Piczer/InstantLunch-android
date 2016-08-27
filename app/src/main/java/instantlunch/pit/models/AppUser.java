package instantlunch.pit.models;

/**
 * Created by Piotr on 2016-06-27.
 */
public class AppUser {
    private static AppUser ourInstance = new AppUser();
    private User user;

    public static AppUser getInstance() {
        return ourInstance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private AppUser() {
    }
}
