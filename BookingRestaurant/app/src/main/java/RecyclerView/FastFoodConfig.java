package RecyclerView;

import android.content.Context;
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

public class FastFoodConfig {
    private Context context;
    private FastFoodAdapter fastFoodAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Food> foods, List<String> keys){
        this.context = context;
        fastFoodAdapter = new FastFoodAdapter(foods, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(fastFoodAdapter);
    }
    class FastFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView tvName, tvPrice;
        private String key;

        public FastFoodViewHolder(ViewGroup parent){
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

    class FastFoodAdapter extends RecyclerView.Adapter<FastFoodViewHolder>{
        private List<Food> listFastFood;
        private List<String> keys;

        public FastFoodAdapter(List<Food> listFastFood, List<String> keys) {
            this.listFastFood = listFastFood;
            this.keys = keys;
        }

        @NonNull
        @Override
        public FastFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FastFoodViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull FastFoodViewHolder holder, int position) {
            holder.bind(listFastFood.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return listFastFood.size();
        }
    }
}
