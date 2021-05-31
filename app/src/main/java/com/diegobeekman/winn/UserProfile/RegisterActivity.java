package com.diegobeekman.winn.UserProfile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diegobeekman.winn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText, professionEditText, workEditText, passwordEditText;
    private EditText phoneEditText, emailEditText;
    private ImageView picImageView;
    private CheckBox maleCheckBox, femaleCheckBox;
    private Button registerButton;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS = "Employees";
    private String TAG = "RegisterActivity";
    private String username, email, profession, phone, workplace;
    private static String fname;
    private String password;
    private com.diegobeekman.winn.UserProfile.Employees employees;
    private FirebaseAuth mAuth;
    TextView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.fullname_edittext);
        professionEditText = findViewById(R.id.profession_edittext);
        workEditText = findViewById(R.id.workplace_edittext);
        phoneEditText = findViewById(R.id.phone_edittext);
        passwordEditText = findViewById(R.id.enterpass_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        picImageView = findViewById(R.id.pic_imageview);
        maleCheckBox = findViewById(R.id.Porter_checkbox);
        femaleCheckBox = findViewById(R.id.Super_checkbox);
        registerButton = findViewById(R.id.register_button);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                if(emailEditText.getText().toString() != null && passwordEditText.getText().toString() != null) {

                    /* here we get the name from the user when the user enters it */
                    fname = nameEditText.getText().toString();


                    Intent intentname = new Intent(getApplicationContext(), getthename.class);
                    intentname.putExtra("newname", String.valueOf(fname));

                    email = emailEditText.getText().toString();
                    phone = phoneEditText.getText().toString();
                    profession = professionEditText.getText().toString();
                    workplace = workEditText.getText().toString();
                    password = passwordEditText.getText().toString();
                    employees = new Employees(fname, email, profession, workplace, phone);
                    registerUser();
                }
            }
        });

    }

    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        GreetTheUser(user);
                        updateUI(user);

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(com.diegobeekman.winn.UserProfile.RegisterActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }

    /**
     * adding user information to database and redirect to login screen
     * @param currentUser */
    public void updateUI(FirebaseUser currentUser) {
        Log.i("data value ", fname);
        String keyid = mDatabase.push().getKey();
        mDatabase.child(keyid).setValue(employees); //adding user info to database

        /* here below we are going to send the name 'fname obtain from above, to the MainActivity *
           we the help of the loginIntent
         */
//        Intent loginIntent = new Intent(this, com.example.schedule.MainActivity.class);
//        loginIntent.putExtra("userfullname", String.valueOf(fname));
//        startActivity(loginIntent);
    }

    public void GreetTheUser(FirebaseUser User) {
        try {
            if (User != null)
                User.sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");

                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                            com.diegobeekman.winn.UserProfile.RegisterActivity.this);

                                    // set title
                                    alertDialogBuilder.setTitle("Please Verify Your EmailID");

                                    // set dialog message
                                    alertDialogBuilder
                                            .setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                            .setCancelable(false)
                                            .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    Intent signInIntent = new Intent(com.diegobeekman.winn.UserProfile.RegisterActivity.this,
                                                            com.diegobeekman.winn.UserProfile.LoginMain.class);
//                                                    com.example.schedule.RegisterActivity.this.finish();

                                                    signInIntent.putExtra("userfullname", String.valueOf(fname));
                                                    startActivity(signInIntent);
                                                    finish();
                                                }
                                            });

                                    // create alert dialog
                                    AlertDialog alertDialog = alertDialogBuilder.create();

                                    // show it
                                    alertDialog.show();


                                }
                            }
                        });

        } catch (Exception e) {
            errorView.setText(e.getMessage());
        }


    }

}
