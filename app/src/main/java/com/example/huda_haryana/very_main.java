package com.example.huda_haryana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

public class very_main extends AppCompatActivity {

    ProgressBar progressBar;
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private EditText Email, Password;
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();




    findViewById(R.id.login_txt).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), Signupcode.class));
            finishAffinity();
        }
    });
    findViewById(R.id.login_b).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login();
        }
    });

    }
    private void login() {
        progressBar.setVisibility(View.VISIBLE);
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(email.isEmpty()){
            progressBar.setVisibility(View.GONE);
            Email.setError("Email required");
            Email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            progressBar.setVisibility(View.GONE);
            Password.setError("Password required");
            Password.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            progressBar.setVisibility(View.GONE);
            Email.setError("Valid Email required");
            Email.requestFocus();
            return;
        }
        if(password.length()<6){
            progressBar.setVisibility(View.GONE);
            Password.setError("Minimum length of password should be 6");
            Password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(very_main.this, MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(very_main.this, "Some temporary error occurred!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(very_main.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });

    }


//    private void FirebaseGoogleAuth(GoogleSignInAccount acct){
//        if(acct != null){
//            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getId(), null);
//            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        Toast.makeText(very_main.this, "Successful", Toast.LENGTH_SHORT).show();
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        updateUI(user);
//                    }
//                    else{
//                        Toast.makeText(very_main.this, "Failed", Toast.LENGTH_SHORT).show();
//                        updateUI(null);
//                    }
//                }
//            });
//        }
//    }


}
