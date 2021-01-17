package FoodFirebaseHelper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Model.Food;

public class SoupFirebase {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Food> foodList = new ArrayList<Food>();

    public interface  DataStatus{
        void DataLoaded(List<Food> foods, List<String> key);
    }

    public SoupFirebase(){
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("foods");
    }

    public void getList(DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Food food = keyNode.getValue(Food.class);
                    if(food.getCategoryId() == 4){
                        foodList.add(food);
                    }
                }
                dataStatus.DataLoaded(foodList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
