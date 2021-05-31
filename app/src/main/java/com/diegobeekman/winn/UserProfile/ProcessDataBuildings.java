package com.diegobeekman.winn.UserProfile;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class ProcessDataBuildings {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBuildings;
    final String newname;

    private List<Buildings> buildings = new ArrayList<>();
//    public static String mname = newname;

    /* this method declares a list of methods to be later use  */
    public interface DataStatus{
        void DataIsLoaded(List<Buildings> buildings, List<String> bkeys, String name);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
   }
   public ProcessDataBuildings(String name) {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBuildings = mDatabase.getReference("Schedule");
        newname = name;
   }

   /* this method is in charge  of reading all the data */
    /* this method is use from 2 different files "PorterListActivity" and "BuildingListActivity" to inorder to
      read data for both files
     */
   public void readBuildings(final DataStatus dataStatus) {
    mReferenceBuildings.addValueEventListener(new ValueEventListener() {
        private String TAG;

//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            contacts.clear();
//            List<String> keys = new ArrayList<>();
//            for (DataSnapshot keyNode : dataSnapshot.getChildren()) {   /*
//                the DataSnapshot which contain all the data is place into a for-loop to
//                get all the sub trees of the json structure until there is not more
//                that is why getChildren is method was used;"its a little cryptic but
//                what it means is this", get all the subtrees until there is not more"
//                */
//                keys.add(keyNode.getKey());                             /*
//                this getkey return the name of each subtree, remember that each element retuned by the
//                getChildren() method; in this case "keyNode" is itself a DataSnoshot instance
//                (is in itself an entire structure) that means not the actual name therefore
//                this add the name of each subtree to the array list */
//                Contact contacts = keyNode.getValue(Contact.class);   /*
//                this use a model like rest, in this case "contacts" which has pre-package list of
//                methods and variables that consides with each designed elements in each subtree
//                for each element like content and value in order to obtain each value of each subtree.
//                in each iteration*/
//
///               contacts.add(contacts);       /* this will add the entire model containing each element
//                /* that is return, each value and content field for each subtree as it iterate */
//            }
//            dataStatus.DataIsLoaded(contacts, keys);
//        }
//


        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            buildings.clear();      /*take a note here */
            List<String> keys = new ArrayList<>();   /* this method is used to contain each subtree */
//            String newname = "Andre Avin";
//            String newname = name;
            for (DataSnapshot keyNode : dataSnapshot.getChildren()) {

                /* get the name of the new register person (that is from the registerActivity file)  */

//                Intent intent = getIntent();
//                Intent intent = Intent.getIntent();
//                Intent intent = getIntent();
//                String name = intent.getStringExtra("fname");
//                Log.d(TAG, String.valueOf(name));


                /* get an specific sub-tree of the structure */
                if (keyNode.getKey().equals(newname)) {
                    keys.add(keyNode.getKey());
                    Log.i(TAG, keyNode.getKey());
                    Buildings building = keyNode.getValue(Buildings.class);
                    buildings.add(building);
                }
        }
        /* this is the end of readPorters method */
            dataStatus.DataIsLoaded(buildings, keys, newname);
            /* this dataStatus method uses its child DataIsLoaded method to */
    }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
      });
    }
 }