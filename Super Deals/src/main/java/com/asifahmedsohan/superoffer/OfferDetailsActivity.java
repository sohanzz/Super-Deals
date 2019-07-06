package com.asifahmedsohan.superoffer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OfferDetailsActivity extends AppCompatActivity {

    TextView detailOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);

        detailOffer=findViewById(R.id.details_offer);
        String newString = (String) getIntent().getExtras().get("position");
        detailOffer.setText(newString);

    }
}
