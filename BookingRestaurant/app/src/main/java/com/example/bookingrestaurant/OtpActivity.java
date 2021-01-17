package com.example.bookingrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private String mOtpToken;

    Button btnVerify;
    EditText edtOtp;
    ProgressBar verifyProgressBar;
    TextView tvWarning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        AnhXa();
        mOtpToken = getIntent().getStringExtra("OTP");
        btnVerify.setOnClickListener(verify);
    }

    private void AnhXa(){
        btnVerify = (Button) findViewById(R.id.btnVerify);
        edtOtp = (EditText) findViewById(R.id.edtOtpNumber);
        verifyProgressBar = (ProgressBar) findViewById(R.id.verify_progress_bar);
        tvWarning = (TextView) findViewById(R.id.tvWaring);
    }

    private View.OnClickListener verify = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String otp = edtOtp.getText().toString();
            if(otp.isEmpty()){
                tvWarning.setText("Vui lòng điền mã xác thực");
                tvWarning.setVisibility(View.VISIBLE);
            }else{

                verifyProgressBar.setVisibility(View.VISIBLE);
                btnVerify.setEnabled(false);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mOtpToken,otp);
                signInWithPhoneAuthCredential(credential);
            }
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            sendUserToHome();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                tvWarning.setText("Đã xảy ra lỗi khi xác thực");
                                tvWarning.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    private void sendUserToHome(){
        startActivity(new Intent(OtpActivity.this, MainActivity.class));
    }
}