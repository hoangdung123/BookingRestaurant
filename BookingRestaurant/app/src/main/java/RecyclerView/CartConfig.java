package RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingrestaurant.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import FoodFirebaseHelper.CartFirebase;
import Model.Food;
import Model.Order;

public class CartConfig {
    private Context context;

    class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView tvName, tvPrice;
        private String key;

        public CartViewHolder(ViewGroup parent){
            super(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
            imgView = (ImageView) itemView.findViewById(R.id.imgFood);
            tvName = (TextView) itemView.findViewById(R.id.textName);
            tvPrice = (TextView) itemView.findViewById(R.id.textPrice);
        }

        public void bind(Food food, String key){
            tvName.setText((food.getName()));
            tvPrice.setText(String.valueOf(food.getPrice()));
            Picasso.with(context).load(food.getImageUrl()).into(imgView);
            this.key = key;
        }
    }

    class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{
        private List<Order> cartList;
        private List<String> keys;

        public CartAdapter(List<Order> cartList, List<String> keys) {
            this.cartList = cartList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CartViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
