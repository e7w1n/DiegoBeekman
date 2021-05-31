package com.diegobeekman.winn.UserProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diegobeekman.winn.R;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.support.v7.app.AppCompatActivity;


/* this RecycleView_config() is the method which setups everything for the recycleview
 * it uses the readPorter method which is the method that reads everything,
 * it takes the DataStatus(() method as a parameter  */

public class RecyclerView_Buildings {
    public String Username;
    private Context mContext;
//    private Porter_list_Adapter mPorterListAdapter;
    private Building_list_Adapter mBuildingListAdapter;
    private RecyclerView Buildings_RecyclerView;

    /* this takes a series of parameters as an argument: recycleView, a context(for the intent), A List for a tow set of class; a porter
    * and onother for buildings*/
    public void setConfig(RecyclerView recyclerView, Context context, List<com.diegobeekman.winn.UserProfile.Buildings> buildings, List<String> bkeys, String name) {

        mContext = context;
        mBuildingListAdapter = new Building_list_Adapter(buildings, bkeys);

        Username = (String) name;
//       Buildings_RecyclerView = (RecyclerView) this.findViewById(R.id.recyclerview_buildings);
//       recyclerView.setHasFixedSize(true);

        mBuildingListAdapter = new Building_list_Adapter((List<com.diegobeekman.winn.UserProfile.Buildings>) buildings, bkeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBuildingListAdapter);

    }

    class BuildingItemView extends RecyclerView.ViewHolder {   /* note this here */

        private TextView txvBuilding1;
        private TextView txvBuilding2;
        private TextView txvBuilding3;
        private TextView txvBuilding4;
        private TextView txvBuilding5;
        private TextView txvBuilding6;
        private TextView txvName;

        private TextView txvUserFullName;

        private String key;

        public BuildingItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.userprofile_list_item, parent, false));


            // list of buildings
            txvBuilding1 = (TextView) itemView.findViewById(R.id.Building1_txtView);
            txvBuilding2 = (TextView) itemView.findViewById(R.id.Building2_txtView);
            txvBuilding3 = (TextView) itemView.findViewById(R.id.Building3_txtView);
            txvBuilding4 = (TextView) itemView.findViewById(R.id.Building4_txtView);
            txvBuilding5 = (TextView) itemView.findViewById(R.id.Building5_txtView);
            txvBuilding6 = (TextView) itemView.findViewById(R.id.Building6_txtView);

            txvName = (TextView) itemView.findViewById(R.id.name_textview);
//            txvUserFullName = (TextView) itemView.findViewById(R.id.fullname_edittext);
        }

        public void bind(com.diegobeekman.winn.UserProfile.Buildings buildings_list, String key) {  //what is this key String for ?
            txvBuilding1.setText(buildings_list.getBuilding1());  //
            txvBuilding2.setText(buildings_list.getBuilding2());  //
            txvBuilding3.setText(buildings_list.getBuilding3());  //
            txvBuilding4.setText(buildings_list.getBuilding4());  //
            txvBuilding5.setText(buildings_list.getBuilding5());  //
            txvBuilding6.setText(buildings_list.getBuilding6());  //

            txvName.setText(buildings_list.getName());

//            txvUserFullName.setText(Username);
            this.key = key;
        }
    }

    /* this is the adapter for the recicleview */
    /* this is where the list adapter is created, that is why is able to creae an adapter upthere using "BookAdapter as a keyword"

     */
    class Building_list_Adapter extends RecyclerView.Adapter<BuildingItemView>{
            private List<com.diegobeekman.winn.UserProfile.Buildings> mBuildingList;   /* a list structure to store for each porter */
            private List<String> bKeys;

        private List<com.diegobeekman.winn.UserProfile.Buildings> mBUildingList;   /* a list structure to store for each building */


        public Building_list_Adapter(List<com.diegobeekman.winn.UserProfile.Buildings> mBuildingList, List<String> bkeys) {
            this.mBuildingList = mBuildingList;
            this.bKeys = bKeys;
        }
        @NonNull
        @Override
        public BuildingItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BuildingItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BuildingItemView holder, int position) {
//                holder.bind(mBookList.get(position), mKeys.get(position));
            com.diegobeekman.winn.UserProfile.Buildings buildings = (mBuildingList.get(position)); /* use the model "Book" and the lIST "mBookList that was createed
                abvove to get the position each row */


                /* list of buildings */
            TextView building1_textview = holder.txvBuilding1;
            building1_textview.setText(buildings.getBuilding1());

            TextView building2_textview = holder.txvBuilding2;
            building2_textview.setText(buildings.getBuilding2());

            TextView building3_textview = holder.txvBuilding3;
            building3_textview.setText(buildings.getBuilding3());

            TextView building4_textview = holder.txvBuilding4;
            building4_textview.setText(buildings.getBuilding4());

            TextView building5_textview = holder.txvBuilding5;
            building5_textview.setText(buildings.getBuilding5());

            TextView building6_textview = holder.txvBuilding6;
            building6_textview.setText(buildings.getBuilding6());

//            TextView userFullname_txtview = findViewById(R.id)
//            userFullname_txtview.setText(Username);

            TextView name_textview = holder.txvName;
            name_textview.setText(buildings.getName());   // maybe Username
        }

        @Override
        public int getItemCount() {
            return mBuildingList.size();
        }
    }
}