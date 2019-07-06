package com.asifahmedsohan.superoffer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TopDealsActivity extends AppCompatActivity {

    private RecyclerView topOffers;
    private List<Offer> OfferList=new ArrayList<>();

    DatabaseReference databaseReference;

    RecyclerViewAdapter recyclerAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_deals);

        retrievedata();


        topOffers=(RecyclerView)findViewById(R.id.top_offers);


        recyclerAdapter=new RecyclerViewAdapter(this,OfferList);
        topOffers.setLayoutManager(new LinearLayoutManager(this));
        topOffers.setAdapter(recyclerAdapter);


    }

    private void retrievedata() {

        databaseReference=FirebaseDatabase.getInstance().getReference().child("topOffers");
        databaseReference.keepSynced(true);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                OfferList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Offer offer1 = dataSnapshot1.getValue(Offer.class);
                    OfferList.add(offer1);
                }
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
