package com.diegobeekman.winn.UserProfile;

import android.content.Intent;
import android.os.Bundle;

import com.diegobeekman.winn.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


//    ArrayList<itemModel> arrayList, arrayList1;
    RecyclerView recyclerView, recyclerView1;
//    int icons[] = {R.drawable.chrome,R.drawable.google_drive,R.drawable.facebook,R.drawable.twitter,R.drawable.google_maps,R.drawable.whatsapp,R.drawable.linkedin,R.drawable.google_plus,R.drawable.instagram};
//    String iconsName[] = {"Chrome", "Google Drive", "Facebook", "Twitter", "Google Maps", "WhatsApp", "LinkedIn", "Google+", "Instagram"};
//    String name[] = { "Alex Thomson", "Steven Croft", "Rob Jones", "Stephen Parry", "James Anderson", "Sam Hain"};
//    String rating[] = {"3.4", "4.3", "3.0", "3.0", "4.5", "4.0"};
//    String price[] = {"100$", "50$", "40$", "60$", "20$", "35$"};
//    String service[] = {"Android App Developer", "Web Developer", "Android App Developer", "Web Developer", "Android App Developer","Web Developer"};

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        recyclerView = (RecyclerView) findViewById(R.id.profile_recyclerview);
        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
//        arrayList = new ArrayList<>();
//        arrayList1 = new ArrayList<>();

//        addPorters();
//        addIcons();
//        addServices();

        /* here we reacive a name obtain from the file 'specified below' the
         *
         */

        @SuppressWarnings("deprecation")
        Intent intent = getIntent();

//        new com.example.diegobeekman_basicactivity.UserProfile.BuildingListActivity.MainActivity();
//        String fname = com.example.schedule.MainActivity.getData();
        String name = intent.getStringExtra("userfullname");

//        String email = intent.getStringExtra("email");
//        addBuildings(fname);
        addBuildings(name);
    }

//    public void addPorters() {
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        new com.example.schedule.Buildings.ProcessDataPorters().readPorters(new com.example.schedule.Buildings.ProcessDataPorters.DataStatus() {
//            @Override
//            public void DataIsLoaded(List<com.example.schedule.Buildings.Porter> porters, List<String> keys) {
//                /* this invoked the seConfig method which is the method that sets up the recycleview */
//                new com.example.schedule.Buildings.RecyclerView_Porters().setConfig(recyclerView, getApplicationContext(), porters, keys);
//            }
//
//            @Override
//            public void DataIsInserted() {
//
//            }
//
//            @Override
//            public void DataIsUpdated() {
//
//            }
//
//            @Override
//            public void DataIsDeleted() {
//
//            }
//        });
//    }
//
//    public void addIcons() {
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        for (int i = 0; i < icons.length; i++) {
//            itemModel itemModel = new itemModel();
//            itemModel.setImage(icons[i]);
//            itemModel.setName(iconsName[i]);
//            arrayList.add(itemModel);
//        }
//
//        IconsAdapter adapter = new IconsAdapter(getApplicationContext(), arrayList);
//        recyclerView.setAdapter(adapter);
//    }

    /* here, we pass the name variable of the currently login user gotten from
     * above
     */
    public void addBuildings(String name) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /* here, we also pass the name also gotten from above to the ProcessDataBuildings()
         * so that later we can process the user on the database therein after ProcessDataBuildings
         * */
        new com.diegobeekman.winn.UserProfile.ProcessDataBuildings(name).readBuildings(new com.diegobeekman.winn.UserProfile.ProcessDataBuildings.DataStatus() {
            @Override
            public void DataIsLoaded(List<Buildings> buildings, List<String> bkeys, String name) {
                new com.diegobeekman.winn.UserProfile.RecyclerView_Buildings().setConfig(recyclerView, getApplicationContext(), buildings, bkeys, name);

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
        String newname = name;
//        Log.v("TAG", name);
    }


//    public void addServices() {
//        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView1.setItemAnimator(new DefaultItemAnimator());
//        for (int i = 0; i < name.length; i++) {
//            itemModel itemModel = new itemModel();
//            itemModel.setName(name[i]);
//            itemModel.setRating(rating[i]);
//            itemModel.setPrice(price[i]);
//            itemModel.setService(service[i]);
//            arrayList1.add(itemModel);
//        }
//
//        ServicesAdapter adapter = new ServicesAdapter(getApplicationContext(), arrayList1);
//        recyclerView1.setAdapter(adapter);
//    }

}

