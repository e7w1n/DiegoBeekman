package com.diegobeekman.winn.Login_Signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.diegobeekman.winn.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.VISIBLE;

public class WelcomeActivity extends AppCompatActivity {


    public Integer REQUEST_EXIT = 9;
    public FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    Button signUpButton;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        mAuth = FirebaseAuth.getInstance();


        signUpButton = findViewById(R.id.welcomeSignUpButton);
        signInButton = findViewById(R.id.welcomeSignInButton);

//        signInButton.setVisibility(INVISIBLE);
//        signUpButton.setVisibility(INVISIBLE);

        signInButton.setVisibility(VISIBLE);
        signUpButton.setVisibility(VISIBLE);

        if (mAuth.getCurrentUser() != null) {
            mAuth.getCurrentUser().reload().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    currentUser = mAuth.getCurrentUser();


                    if (currentUser != null && currentUser.isEmailVerified()) {


                        System.out.println("Email Verified : " + currentUser.isEmailVerified());

                        Intent MainActivity = new Intent(com.diegobeekman.winn.Login_Signup.WelcomeActivity.this, com.diegobeekman.winn.Login_Signup.LoginMainActivity.class);
                        startActivity(MainActivity);
                        com.diegobeekman.winn.Login_Signup.WelcomeActivity.this.finish();


                    }
                }
            });

        } else {

            signInButton.setVisibility(VISIBLE);
            signUpButton.setVisibility(VISIBLE);

            System.out.println("user not available");

        }

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent signUpIntent = new Intent(com.diegobeekman.winn.Login_Signup.WelcomeActivity.this, com.diegobeekman.winn.Login_Signup.SignUpActivity.class);

                startActivity(signUpIntent);


            }
        });


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent signInIntent = new Intent(com.diegobeekman.winn.Login_Signup.WelcomeActivity.this, com.diegobeekman.winn.Login_Signup.SignInActivity.class);

                startActivityForResult(signInIntent, REQUEST_EXIT);


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();

            }
        }
    }



}
