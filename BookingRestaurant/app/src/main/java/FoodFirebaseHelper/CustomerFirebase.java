package FoodFirebaseHelper;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Model.Customer;

public class CustomerFirebase {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    public CustomerFirebase(){
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
    }

    public interface DataStatus{
        void DateLoaded();
        void DataInserted();
        void DataUpdated();
        void DataDeleted();
    }

    public void getCurrentCustomer(final DataStatus dataStatus){

    }

    public void addCustomer(Customer customer, final DataStatus dataStatus){

    }
}
