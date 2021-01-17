package FoodFirebaseHelper;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Model.Customer;

public class CustomerFirebase {
    private FirebaseFirestore mFirebase;
    private DocumentReference mDocument;

    public CustomerFirebase(){
        mFirebase = FirebaseFirestore.getInstance();
        mDocument = mFirebase.document("orderFood/customers");
    }

    public interface DataStatus{
        void DateLoaded();
        void DataInserted();
        void DataUpdated();
        void DataDeleted();
    }

    public void addCustomer(Customer customer, final DataStatus dataStatus){
        mDocument.collection("customer")
                .add(customer)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        dataStatus.DataInserted();
                    }
                });
    }
}
