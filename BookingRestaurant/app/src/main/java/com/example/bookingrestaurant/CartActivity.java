package com.example.bookingrestaurant;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import FoodFirebaseHelper.CustomerFirebase;
import Model.Customer;
import Model.Order;

public class CartActivity extends AppCompatActivity {
    private Button btnCheckOut;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        AnhXa();
        btnCheckOut.setOnClickListener(showModal);
    }

    private void AnhXa(){
        btnCheckOut = (Button) findViewById(R.id.btnCheckout);
    }

    private View.OnClickListener showModal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
              CartActivity.this, R.style.BottomSheetDialogTheme
            );

            View bottomView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.order_info_bottom_sheet,
                    (LinearLayout) findViewById(R.id.bottomSheetContainer));
            EditText edtName = bottomView.findViewById(R.id.edtName);
            EditText edtAddress = bottomView.findViewById(R.id.edtAddress);
            EditText edtPhone = bottomView.findViewById(R.id.edtPhone);
            bottomView.findViewById(R.id.btnPay).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Customer customer = new Customer();
                    customer.setFullName(edtName.getText().toString());
                    customer.setAddress(edtAddress.getText().toString());
                    customer.setPhoneNumber(edtPhone.getText().toString());
                    new CustomerFirebase().addCustomer(customer, new CustomerFirebase.DataStatus() {
                        @Override
                        public void DateLoaded() {

                        }

                        @Override
                        public void DataInserted() {
                            Dialog dialog = new Dialog(CartActivity.this);
                            dialog.setContentView(R.layout.success_dialog);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.setCancelable(false);
                            Button btnClose = dialog.findViewById(R.id.btnClose);
                            btnClose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                            bottomSheetDialog.dismiss();
                        }

                        @Override
                        public void DataUpdated() {

                        }

                        @Override
                        public void DataDeleted() {

                        }
                    });

                }
            });
            bottomSheetDialog.setContentView(bottomView);
            bottomSheetDialog.show();
        }
    };
}
