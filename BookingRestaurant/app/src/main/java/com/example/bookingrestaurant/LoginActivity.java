package com.example.bookingrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static com.example.bookingrestaurant.R.layout.activity_login;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callBacks;
    private String token;
    TextView tvFeedback;
    Button btnLogin;
    EditText edtPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);
        mAuth = FirebaseAuth.getInstance();
        tvFeedback = (TextView) findViewById(R.id.tvFeedback);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        btnLogin.setOnClickListener(login);
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
                Intent otpIntent = new Intent(LoginActivity.this, OtpActivity.class);
                otpIntent.putExtra("OTP", s);
                startActivity(otpIntent);
            }
        };
    }

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phone = "+84" + edtPhoneNumber.getText().toString();
            if(phone.isEmpty())
            {
                tvFeedback.setText("Vui lòng điền số điện thoại");
                tvFeedback.setVisibility(View.VISIBLE);
            }
            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                    .setActivity(LoginActivity.this)
                    .setPhoneNumber(phone)
                    .setTimeout(5L, TimeUnit.SECONDS)
                    .setCallbacks(callBacks)
                    .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        }
    };

}