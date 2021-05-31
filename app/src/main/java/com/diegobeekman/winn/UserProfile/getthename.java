package com.diegobeekman.winn.UserProfile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.diegobeekman.winn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;

public class getthename extends AppCompatActivity {

    private static String email, fname;
    private FirebaseAuth mAuth;
    private static final String EMPLOYEES = "Employees/Porters";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getthename);
        //receive data from login screen

//        Intent intent = getIntent();
//        email = intent.getStringExtra("email");
//        fname = intent.getStringExtra("UserFullName");


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser;
//        //checking if user is already logged in, if he is,then the mothod below will take us to.... */
        if (mAuth.getCurrentUser() != null) {
            mAuth.getCurrentUser();
//            Log.d(TAG, String.valueOf(mAuth.getCurrentUser()));
            String myuser = String.valueOf(mAuth.getCurrentUser());
        }
        firebaseUser = mAuth.getCurrentUser();
        email = firebaseUser.getEmail();
//
//        /* here we recieved the  email from the user that was passed from another file (MainActivity)"*/
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        fname = intent.getStringExtra("uSerFullName");
//
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(EMPLOYEES);
        Log.v("USERID", userRef.getKey());
        String mylog = userRef.getKey();

        // Read from the database
        userRef.addValueEventListener(new ValueEventListener() {
            String mail, profession, workplace, phone;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId : dataSnapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(email)) {
                        fname = keyId.child("fullName").getValue(String.class);

                        Log.v("USERNAME", fname);
                        /* here we pass the full name of the user to the MainActivity so that everytime
                         tbe user login we can know who login ins
                         */
                        Intent fnameintent = new Intent(getApplicationContext(), MainActivity.class);
                        fnameintent.putExtra("userfullname", fname);

//                        getUserName(fname);

                        break;
                    }
                }
//                videoTxtView.setText(phone);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

//    }
//

    }
    private static String seconddata = fname;
    private static String data = "kenny";
    public static String getData() {
        return data;
    }
}