package RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingrestaurant.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Food;

public class FoodConfig {
    private Context context;
    private FoodAdapter foodAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Food> foods, List<String> keys){
        this.context = context;
        foodAdapter = new FoodAdapter(foods, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(foodAdapter);
    }
    class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView tvName, tvPrice;
        private String key;

        public FoodViewHolder(ViewGroup parent){
            super(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
             imgView = (ImageView) itemView.findViewById(R.id.imageView);
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

    class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder>{
        private List<Food> foodList;
        private List<String> keys;

        public FoodAdapter(List<Food> foodList, List<String> keys){
            this.foodList = foodList;
            this.keys = keys;
        }
        @NonNull
        @Override
        public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FoodViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
            holder.bind(foodList.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return foodList.size();
        }
    }
}
