package FoodFirebaseHelper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import Model.Food;

public class FoodFirebase {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private DocumentReference mDocReference = FirebaseFirestore.getInstance().document("orderFood/user");
    private List<Food> foodList = new ArrayList<Food>();

    public interface  DataStatus{
        void DataLoaded(List<Food> foods, List<String> key);
    }
    public FoodFirebase(){
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
                foodList.add(food);
            }
            dataStatus.DataLoaded(foodList, keys);
        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
