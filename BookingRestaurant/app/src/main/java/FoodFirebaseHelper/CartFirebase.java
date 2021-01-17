package FoodFirebaseHelper;

import androidx.annotation.NonNull;

import com.example.bookingrestaurant.CartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import Model.Customer;
import Model.Food;
import Model.Order;

public class CartFirebase {
    private String collection = "orderFood";
    private String documentCustomer = "customer";
    private String documentCart = "cart";
    private FirebaseFirestore mFirebaseFirestore;
    private DocumentReference mDoc;

    public interface DataStatus{
        void DataLoaded();
        void DataInserted();
    }
    public CartFirebase(){
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        mDoc = mFirebaseFirestore.document("orderFood/order");
    }

    public void getCart(Customer customer, Food food, final DataStatus dataStatus){
        String customerPhone = customer.getPhoneNumber();
        mDoc.collection(collection)
                .whereEqualTo(customerPhone, true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){

                            dataStatus.DataLoaded();
                        }
                    }
                });
    }
    public void addToCart(Order order, final DataStatus dataStatus){
        mFirebaseFirestore.collection("customer")
            .add(order).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                    dataStatus.DataInserted();
            }
        });
    }

}
