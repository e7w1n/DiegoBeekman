package com.diegobeekman.winn.UserProfile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

//import android.support.v7.app.AppCompatActivity;

public class BuildingListActivity extends AppCompatActivity {

    private RecyclerView Buildings_RecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_list_item);   /* the file we are using here is this one, with the recyclerview
                                                         to display all the list of buildings */

        Buildings_RecyclerView = (RecyclerView) this.findViewById(R.id.recyclerview_buildings);  // the recyclerview we are using is in
                                                                                                /* insde the file above and not

         /* it uses the readPorter method which is the method that reads everything,
         * it takes the DataStatus(() method as a parameter  */
        /* here we declared name containing a null valus so that in case nothing is pass it does not crash
         */
        String name = null;
        new ProcessDataBuildings(name).readBuildings(new ProcessDataBuildings.DataStatus() {
            @Override
            public void DataIsLoaded(List<Buildings> buildings, List<String> bkeys, String name) {
                new RecyclerView_Buildings().setConfig(Buildings_RecyclerView, com.diegobeekman.winn.UserProfile.BuildingListActivity.this, buildings, bkeys, name);

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    public static class MainActivity extends AppCompatActivity {

        private TextView titleTextView, registerTextView, forgetPassTextView;
        private EditText emailEditText, passwordEditText;
        private ImageView logoImageView;
        private Button loginButton;
        private FirebaseAuth mAuth;
        private String email, password;
        public static String UserFullName;
        private static final String TAG = "MainActivity";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.loginscreen_activity);

            titleTextView = findViewById(R.id.title_textview);
            registerTextView = findViewById(R.id.register_textview);
            forgetPassTextView = findViewById(R.id.forget_password_textview);
            emailEditText = findViewById(R.id.emailogin_edittext);
            passwordEditText = findViewById(R.id.password_edittext);
            logoImageView = findViewById(R.id.logo_imageview);
            loginButton = findViewById(R.id.button);

            mAuth = FirebaseAuth.getInstance();


            //checking if user is already logged in, if he is then the mothod below will take us to.... */
    //        if (mAuth.getCurrentUser() != null) {
    //            Log.d(TAG, String.valueOf(mAuth.getCurrentUser()));
    //            String myuser = String.valueOf(mAuth.getCurrentUser());
    //            updateUI(mAuth.getCurrentUser());
    //        }

            /* set up a button and if the user click then login*/

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = emailEditText.getText().toString(); /* get data to this field from an already set data(that is the xml edits) */
                    password = passwordEditText.getText().toString(); /* get data to this field from an already set data(that is the xml edits) */

                    mAuth.signInWithEmailAndPassword(email, password) /* use the firebase authentication object to sumun this method signWithEmailAndPassword
                      add pass these 2 parameters */

                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

    //                                    String nameofUser = String.valueOf(mAuth.getCurrentUser());
    //                                    new GetUserName();
    //                                    String nameofUser = GetUserName.getData();
    //                                    Log.i("data value ", nameofUser);



                                        /* check to see if the user is already at the database,
                                        if so, we will get the name from this activity below
                                         */

                                        GetName(user);

    //                                  startActivity(checkifalreadylogin);
    //                                  Log.v("DATA", UserFullName);

    //        Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
    //        profileIntent.putExtra("email", currentUser.getEmail());

                                       /*If its the first time the user uses this, then
                                         here we recieve the user's name directly from the Register
                                         Activity */

    //                                    Intent intent = getIntent();
    //                                    UserFullName = intent.getStringExtra("userfullname");
    //                                  Log.v("DATA", UserFullName);

                                        if (UserFullName != null) {
                                            ToLaunchToDisplay(user);
                                        }


    //                                    Intent showbuildings = new Intent(getApplicationContext(), com.example.schedule.Buildings.MainActivity.class);
                                        /*here we send the user's name gotten from the register activity */
    //                                    showbuildings.putExtra("userfullname", UserFullName);
    //                                    showbuildings.putExtra("email", user.getEmail());
    //                                    startActivity(showbuildings);

    //                                    new getthename();
    //                                    String data = getthename.getData();
    //                                    Log.i("data value ", data);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                    }); // end of the addOnCompleteListener method
                } // end of the onclick method
            }); // end of the on click method

            registerTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(registerIntent);
                }
            });
        }

        @Override
        public void onStart() {
            super.onStart();
    //        // Check if user is signed in (non-null) and update UI accordingly.
    //        FirebaseUser currentUser = mAuth.getCurrentUser();
    //        if (currentUser != null) {
    //            updateUI(currentUser);
    //        }
    //        GetName(currentUser);
    //
        }

    //    @Override
    //    protected void onResume() {
    //        super.onResume();
    //        // Check if user is signed in (non-null) and update UI accordingly.
    //        FirebaseUser currentUser = mAuth.getCurrentUser();
    //        if (currentUser != null) {
    //            updateUI(currentUser);
    //        }
    //    }

    public void GetName(FirebaseUser currentUser) {
        /* here the email gotten from the user is pass to another file */
        Intent checkifalreadylogin = new Intent(getApplicationContext(), com.diegobeekman.winn.UserProfile.LoginGetUserName.class);
        checkifalreadylogin.putExtra("email", currentUser.getEmail());
        startActivityForResult(checkifalreadylogin, 2);

    }

        /* this method will take us to another activity  and read information from this user*/
        public void ToLaunchToDisplay(FirebaseUser currentUser) {
            /* here the email gotten from the user is pass to another file */
    //        Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
    //        profileIntent.putExtra("email", currentUser.getEmail());

    //        Intent logingetusername = new Intent(getApplicationContext(), LoginGetUserName.class);
    //        logingetusername.putExtra("email", currentUser.getEmail());

            Intent showbuildings = new Intent(this, com.diegobeekman.winn.UserProfile.MainActivity.class);
            /*here we send the user's name gotten from the register activity */
            showbuildings.putExtra("userfullname", UserFullName);
            showbuildings.putExtra("email", currentUser.getEmail());

    //      Intent showbuildings = new Intent(this, com.example.schedule.ProfileActivity.class);

    //        Intent showbuildings = new Intent(this, com.example.schedule.Buildings.MainActivity.class);
    //        showbuildings.putExtra("userfullname", UserFullName);
    //        showbuildings.putExtra("email", currentUser.getEmail());

    //        Intent intent = getIntent();
    //        UserFullName = intent.getStringExtra("userfullname");
    //        Log.v("DATA", UserFullName);

    //        Log.v("DATA", currentUser.getUid());
    //        String mylog = currentUser.getUid();
    //          startActivity(profileIntent);
            startActivity(showbuildings);
    //        startActivity(logingetusername);
        }


    //    private void startActivityForResult(Intent checkifalreadylogin) {
    //        String newname=checkifalreadylogin.getStringExtra("userFullName");
    //    }

        public static String getData() {
            return UserFullName;
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // check if the request code is same as what is passed  here it is 2
            if(requestCode==2)
            {
                UserFullName=data.getStringExtra("UserFullName");
                Log.d("thename", UserFullName);

            }
        }
    }
}
