package instantlunch.pit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import instantlunch.pit.R;
import instantlunch.pit.models.Restaurant;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView address;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.restaurant_name_Text);
            address = (TextView) itemView.findViewById(R.id.restaurant_address_Text);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "ID: " + restaurants.get(getLayoutPosition()).id, Toast.LENGTH_SHORT).show();
        }
    }

    private List<Restaurant> restaurants;

    public RestaurantsAdapter(List<Restaurant> restaurants)
    {
        this.restaurants = restaurants;
    }
    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View restaurantView = inflater.inflate(R.layout.item_list_restuarants, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(restaurantView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantsAdapter.ViewHolder holder, int position) {
        Restaurant currentRestaurant = restaurants.get(position);
        TextView name = holder.name;
        TextView address = holder.address;

        name.setText(currentRestaurant.name);
        String currentRestaurantAddress = currentRestaurant.street + " " + currentRestaurant.house_number;
        if(currentRestaurant.apartment_number != null && currentRestaurant.apartment_number != "" && currentRestaurant.apartment_number != "null")
        {
            currentRestaurantAddress += "/" + currentRestaurant.apartment_number;
        }
        currentRestaurantAddress += ", " + currentRestaurant.city;
        address.setText(currentRestaurantAddress);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
