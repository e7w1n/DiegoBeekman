package com.diegobeekman.winn.UserProfile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegobeekman.winn.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;

/* this file is to check if the user already exist in the database,
  if does already exist, then we just simply get the name
 */


public class LoginGetUserName<newname> extends AppCompatActivity {
    private TextView occupationTxtView, nameTxtView, workTxtView;
    private TextView emailTxtView, phoneTxtView, videoTxtView, facebookTxtView, twitterTxtView;
    private ImageView userImageView, emailImageView, phoneImageView, videoImageView;
    private ImageView facebookImageView, twitterImageView;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String email;
    private String userid;
    private String fname;
    private String newname; // this is we will store the full name of the user
    private static Employees username;
    private static final String EMPLOYEES = "Employees";
    private List<Employees> EmployeesArr = new ArrayList<>(Arrays.asList());   // this will contain the entire structure
//    private List<Employees> EmployeesArr = new ArrayList<>();// this will contain the entire structure

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //receive data from login screen

//        String data = null;
//        email = receiveEmail(data);

        /* here we reacived the  email from the user that was passed from another file (MainActivity)"*/
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
//        Log.d("email", email);
//        fname = intent.getStringExtra("userfullname");



            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference userRef = rootRef.child(EMPLOYEES);
            Log.v("USERID", userRef.getKey());
            String mylog = userRef.getKey();

//        occupationTxtView = findViewById(R.id.occupation_textview);
//        nameTxtView = findViewById(R.id.name_textview);
//        workTxtView = findViewById(R.id.workplace_textview);
//        emailTxtView = findViewById(R.id.email_textview);
//        phoneTxtView = findViewById(R.id.phone_textview);

//        videoTxtView = findViewById(R.id.video_textview);
//        facebookTxtView = findViewById(R.id.facebook_textview);
//        twitterTxtView = findViewById(R.id.twitter_textview);

//        userImageView = findViewById(R.id.user_imageview);
//        emailImageView = findViewById(R.id.email_imageview);

//        phoneImageView = findViewById(R.id.phone_imageview);
//        videoImageView = findViewById(R.id.phone_imageview);
//        facebookImageView = findViewById(R.id.facebook_imageview);
//        twitterImageView = findViewById(R.id.twitter_imageview);

            // Read from the database

            userRef.addValueEventListener(new ValueEventListener() {
                private Object HashMap;
                String profession, workplace, phone;
                String userEnteredEmail;
                String newemail;

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<String> keys = new ArrayList<>();  /* this will contain each subtree */
                    for (DataSnapshot keyNode : dataSnapshot.getChildren()) {

                    /*          the DataSnapshot which contain all the data is place into a for-loop to
//                get all the sub trees of the json structure until there is not more
//                that is why getChildren is method was used;"its a little cryptic but
//                what it means is this", get all the subtrees until there is not more"
//                */
                        keys.add(keyNode.getKey());                             /*
//                this getkey return the name of each subtree, remember that each element retuned by the
//                getChildren() method; in this case "keyNode" is itself a DataSnoshot instance
//                (is in itself an entire structure) that means not the actual name therefore
//                this add the name of each subtree to the array list */

                        Employees newEmployeessRegistered = keyNode.getValue(Employees.class);
//                this use a model like rest, in this case "contacts" which has pre-package list of
//                methods and variables that consides with each designed elements in each subtree
//                for each element like content and value in order to obtain each value of each subtree.
//                in each iteration*/

                /* once we get all the values for each subtree, "we can get an specific value of the struture like the name"
                   but before that
                 */

//                    newemail = String.valueOf(keyNode.child("email").toString());
//                    Log.d(newemail, newemail);
                        EmployeesArr.add(newEmployeessRegistered); /* this will add the entire model containing each element that is return, each value and content field for each subtree as
                       it iterate */

                        /************************** ATTEMPT 13 **************************/


//                    HashMap<String, JSONObject> map = (HashMap<String, JSONObject>) dataSnapshot.getValue();
                        java.util.HashMap<String, String> map = (java.util.HashMap<String, String>) dataSnapshot.getValue();

//                    Converting  to Json
//                    Gson gson = new Gson();
//                    String jsonString = new Gson().toJson(keyNode);
//                    Object object = dataSnajObject.getValue(Object.class);
//                    JSONObject jObject = new JSONObject();

//                    Iterator<?> iterator = jObject.keys();
                        Iterator iterator = map.entrySet().iterator();

                        //label for outer loop
//                    outer :
                        while (iterator.hasNext()) {
//                            String key = (String) iterator.next();

                            Map.Entry pairs = (Map.Entry) iterator.next();
                            System.out.println(pairs.getKey() + " = " + pairs.getValue());

                            System.out.println("----------------------------");
                            /************ now convert a hashmap keys into ArrayList *******/
                            List<String> compNamesList = new ArrayList<String>(map.keySet());
                            for (String s : compNamesList) {
                                System.out.println(s);
                            }
                            System.out.println("----------------------------");
                            // Getting Collection of values from HashMap
                            Collection<String> values = map.values();

                            // Creating an ArrayList of values
//                        ArrayList<JSONObject> listOfValues = new ArrayList<JSONObject>(values);
                            ArrayList<String> listOfValues = new ArrayList<String>(values);

//                        List<Integer> EmpValuesList = new ArrayList<Integer>(map.values());
//                        for (string v : empvalueslist) {
//                            system.out.println(v);
//                        }
                            /******************* Convert data strocture to json Object ****************/
                            /* ***************** get the string values from with the JSON ***********/
//                        JSONArray array = yourJsonObject.optJSONArray("cholesterol");
//                        Gson gson = new Gson();

                            String json = new Gson().toJson(listOfValues);
                            JSONArray jsArray = new JSONArray(listOfValues);
                            if (jsArray != null) {
                                for (int i = 0; i < jsArray.length(); i++) {
                                    JSONObject object = jsArray.optJSONObject(i);
                                    if (object != null) {
                                        // this is where you manipulate all the date, hdl, ldl...etc
                                        // Get value from JSON
                                        try {
                                            userEnteredEmail = (String) object.getString("email");
//                                        Log.d("testing_emai", email);
//                                        Log.d("retriveddate", userEmail);
                                            if (userEnteredEmail.equals(email)) {  /* if the user email equals to the Login Email one inside the database */
                                                newname = (String) object.getString("fullName");
//                                            break outer;
                                                break;
                                            } //end of the if condition
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } //end of the catch
//                                        break;
                                       } // end of the try construct
//                                    break;
//                                if (LoginEmail == keyNode.child("email").getValue().equals(email))
                                } // end of the for-loop
                                break;
                            } // end of the  jsonArray iteration
                            break;
                        } // end of the while (iterator.hashext) loop
                        System.out.println("exit in the outloop");
                        Log.d("outer", "exiting out the outer");
//                        Intent fnameintent = new Intent(getApplicationContext(), MainActivity.class);
//                        fnameintent.putExtra("userfullname", newname);
//                        getUserName(newname);

                        Intent intent=new Intent();
                        intent.putExtra("UserFullName",newname);

                        setResult(2,intent);

                        finish();//finishing activity

                        /* this is for the file below to start after */
                        Intent showbuildings = new Intent(getApplicationContext(), com.diegobeekman.winn.UserProfile.MainActivity.class);
                        /*here we send the user's name gotten from the register activity */
                        showbuildings.putExtra("userfullname", newname);
                        showbuildings.putExtra("email", email);
                        startActivity(showbuildings);
                        /* this is for the file below to start after */
                        break;

//                        String firstTimeloginEmail = (String) keyNode.child("email").getValue().equals(email);
                        /************************** end of ATTEMPT 13 **************************/
                        /********************* i do not longer needs this anymore **************/
//                    if (keyNode.child("email").getValue().equals(email)) {
//                     if (newemail.equals(email)) {
//                            fname = keyNode.child("fullName").getValue(String.class);
//                        username = keys.get(1);
                        /* here we pass the full name of the user to the MainActivity so that everytime
                         tbe user login we can know who login ins
                         */
//                            Intent fnameintent = new Intent(getApplicationContext(), MainActivity.class);
//                            fnameintent.putExtra("userfullname", fname);

//                            getUserName(fname);

//                        profession = keyId.child("profession").getValue(String.class);
//                        workplace = keyId.child("workplace").getValue(String.class);
//                        phone = keyId.child("phone").getValue(String.class);
//                            break;
//                        }
                        /********************* i do not longer needs this anymore **************/
//                nameTxtView.setText(fname);
//                emailTxtView.setText(email);
//                occupationTxtView.setText(profession);
//                workTxtView.setText(workplace);
//                phoneTxtView.setText(phone);
//                videoTxtView.setText(phone);
                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }


    public String getUserName(String data) {
        Intent fnameintent = new Intent(getApplicationContext(), BuildingListActivity.MainActivity.class);
        fnameintent.putExtra("userfullname", newname);
        return data;
    }

    /* all this method does is reacive an string(that is the email) from the com.example.schedule.MainActivity.
       and
     */
    public static String receiveEmail(String email) {
        return email;
    }

//    public void getUserName(String data) {
//        Intent fnameintent = new Intent(getApplicationContext(), MainActivity.class);
//        fnameintent.putExtra("userfullname", newname);
//        startActivity(fnameintent);
////        return data;
//    }
} //end of the LogingetUserName activity

// Generic function to convert set to list
//    public static <T> List<T> convertSetToList(Set<T> set)
//    {
//        // create an empty list
//        List<T> list = new ArrayList<>();
//
//        // push each element in the set into the list
//        for (T t : set)
//            list.add(t);
//
//        // return the list
//        return list;
//    }