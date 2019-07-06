package com.asifahmedsohan.superoffer;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.asifahmedsohan.superoffer.usersession.UserSession;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {


    //ImageButton
    private ImageButton DealsOfTheDay;


    //Fragments_categories
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private SliderLayout sliderShow;


    private AdView mAdView;

   //to get user session data
    private UserSession session;

    private String  first_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DealsOfTheDay=(ImageButton)findViewById(R.id.deal_Of_Day);

        loadBannerAd();

        tabLayout=findViewById(R.id.tab_categories);
        viewPager=findViewById(R.id.view_pager);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());


        DealsOfTheDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TopDeals=new Intent(MainActivity.this,TopDealsActivity.class);
                startActivity(TopDeals);

            }
        });



        //add Fragment Here
        adapter.AddFragment(new FragmentTelecom(),"Telecom");
        adapter.AddFragment(new FragmentFood(),"Food");
        adapter.AddFragment(new FragmentFashion(),"Fashion");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        TextView appname = findViewById(R.id.appname);
        appname.setTypeface(typeface);

        //retrieve session values and display on listviews
        getValues();

        //ImageSLider
        inflateImageSlider();

        if (session.getFirstTime()) {
            //tap target view
            tapview();
            session.setFirstTime(false);
        }




    }

    private void loadBannerAd() {

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void tapview() {

        new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.notifintro), "Notifications", "DEMO TEXT")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.colorAccent)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.accent)
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.first))



                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        session.setFirstTime(false);
                        Toasty.success(MainActivity.this, " You are ready to go !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                    }
                }).start();

    }

    private void getValues() {

        //create new session object by passing application context
        session = new UserSession(getApplicationContext());


    }

    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = findViewById(R.id.slider);


        //populating Image slider
        ArrayList<String> sliderImages = new ArrayList<>();
        sliderImages.add("https://firebasestorage.googleapis.com/v0/b/super-deals-2da8b.appspot.com/o/sliderImages%2Fdemo0.png?alt=media&token=37169aa1-0747-47d0-bc8e-74271149fe20");
        sliderImages.add("https://firebasestorage.googleapis.com/v0/b/super-deals-2da8b.appspot.com/o/sliderImages%2Fdemo1.png?alt=media&token=1268df96-9a50-4691-bf60-cf82de5198b8");


        for (String s : sliderImages) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            Log.d("imagelink","wow");
            sliderView.image(s);
            sliderShow.addSlider(sliderView);
        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }

    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();

    }


}
