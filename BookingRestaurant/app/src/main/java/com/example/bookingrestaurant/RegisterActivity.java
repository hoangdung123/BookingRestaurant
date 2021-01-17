package com.example.bookingrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callBacks;
    Button btnRegister;
    TextView tvWarning;
    EditText edtPhoneRegister, edtCountryCode;
    ProgressBar registerProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        AnhXa();
        btnRegister.setOnClickListener(register);
        callBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Intent otpIntent = new Intent(RegisterActivity.this, OtpActivity.class);
                otpIntent.putExtra("OTP", s);
                startActivity(otpIntent);
            }
        };
    }


    private void AnhXa(){
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvWarning = (TextView) findViewById(R.id.tvWaring);
        edtPhoneRegister = (EditText) findViewById(R.id.edtRegisterPhone);
        edtCountryCode = (EditText) findViewById(R.id.edtCountryCode);
        registerProgressBar = (ProgressBar) findViewById(R.id.register_progress_bar);
    }

    private View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phone_number = "+" + edtCountryCode.getText().toString() + edtPhoneRegister.getText().toString();
            if(phone_number.isEmpty()){
                tvWarning.setVisibility(View.VISIBLE);
                tvWarning.setText("Vui lòng điền đầy đủ số điện thoại");
            }else{
                registerProgressBar.setVisibility(View.VISIBLE);
                btnRegister.setEnabled(false);

                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                        .setActivity(RegisterActivity.this)
                        .setPhoneNumber(phone_number)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setCallbacks(callBacks)
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        }
    };


}