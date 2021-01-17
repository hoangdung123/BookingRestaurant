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

public class RiceConfig {
    private Context context;
    private RiceAdapter riceAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Food> foods, List<String> keys){
        this.context = context;
        riceAdapter = new RiceAdapter(foods, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(riceAdapter);
    }


    class RiceViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView tvName, tvPrice;
        private String key;

        public RiceViewHolder(ViewGroup parent){
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

    class RiceAdapter extends RecyclerView.Adapter<RiceViewHolder>{

        private List<Food> listRice;
        private List<String> keys;

        public RiceAdapter(List<Food> listRice, List<String> keys) {
            this.listRice = listRice;
            this.keys = keys;
        }

        @NonNull
        @Override
        public RiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RiceViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RiceViewHolder holder, int position) {
            holder.bind(listRice.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
