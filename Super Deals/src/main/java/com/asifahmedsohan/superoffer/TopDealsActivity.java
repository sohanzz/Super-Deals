package com.asifahmedsohan.superoffer;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TopDealsActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    DatabaseReference databaseReference;
    RecyclerViewAdapter recyclerAdapter;
    private RecyclerView topOffers;
    private List<Offer> OfferList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_deals);

        retrieveData();

        topOffers = (RecyclerView) findViewById(R.id.top_offers);
        recyclerAdapter = new RecyclerViewAdapter(this, OfferList,this);
        topOffers.setLayoutManager(new LinearLayoutManager(this));
        topOffers.setAdapter(recyclerAdapter);
    }

    private void retrieveData() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("topOffers");
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

    @Override
    public void ItemClickListener(int position) {
        OfferList.get(position);
        Intent intent=new Intent(TopDealsActivity.this, OfferDetailsActivity.class);
        String value=String.valueOf(position);
        intent.putExtra("position",value);
        startActivity(intent);
    }
}
