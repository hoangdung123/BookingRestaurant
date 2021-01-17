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

import FoodFirebaseHelper.MilkteaFirebase;
import Model.Food;

public class MilkteaConfig {

    private Context context;
    private MilkTeaAdapter milkTeaAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Food> foods, List<String> keys){
        this.context = context;
        milkTeaAdapter = new MilkTeaAdapter(foods, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(milkTeaAdapter);
    }
    class MilkteaViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView tvName, tvPrice;
        private String key;

        public MilkteaViewHolder(ViewGroup parent){
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

    class MilkTeaAdapter extends RecyclerView.Adapter<MilkteaViewHolder>{
        private List<Food> listMilktea;
        private List<String> keys;

        public MilkTeaAdapter(List<Food> listMilktea, List<String> keys) {
            this.listMilktea = listMilktea;
            this.keys = keys;
        }

        @NonNull
        @Override
        public MilkteaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MilkteaViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MilkteaViewHolder holder, int position) {
            holder.bind(listMilktea.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return listMilktea.size();
        }
    }
}
