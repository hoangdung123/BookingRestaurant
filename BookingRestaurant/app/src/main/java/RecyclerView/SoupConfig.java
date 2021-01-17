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

public class SoupConfig {
    private Context context;
    private SoupAdapter soupAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Food> foods, List<String> keys){
        this.context = context;
        soupAdapter = new SoupAdapter(foods, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(soupAdapter);
    }

    class SoupViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView tvName, tvPrice;
        private String key;

        public SoupViewHolder(ViewGroup parent){
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

    class SoupAdapter extends RecyclerView.Adapter<SoupViewHolder>{
        private List<Food> listSoup;
        private List<String> keys;

        public SoupAdapter(List<Food> listSoup, List<String> keys) {
            this.listSoup = listSoup;
            this.keys = keys;
        }

        @NonNull
        @Override
        public SoupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SoupViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SoupViewHolder holder, int position) {
            holder.bind(listSoup.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
