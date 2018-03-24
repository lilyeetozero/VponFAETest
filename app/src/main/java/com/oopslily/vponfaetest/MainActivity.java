package com.oopslily.vponfaetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.vpadn.ads.*;

public class MainActivity extends AppCompatActivity implements VpadnAdListener {

    private RelativeLayout adBannerLayout;
    private Button btn_banner;
    private Button btn_interstitial;

    private VpadnBanner vponBanner;
    private VpadnInterstitialAd vponInterstitial;
    private String BannerbannerId = "8a80854b6241cd5d01624dff72d9101f";
    private String InterstitialbannerId = "8a80854b6241cd5d01624dffdfdf1020";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_banner = (Button) findViewById(R.id.btn_banner);
        btn_interstitial = (Button) findViewById(R.id.btn_interstitial);
        btn_interstitial.setText("Interstitial AD Loading");

        vponInterstitial = new VpadnInterstitialAd(this,InterstitialbannerId,"TW");
        // create VpadnInterstitialAd instance
        vponInterstitial.setAdListener(this);
        VpadnAdRequest interstitialRequest = new VpadnAdRequest();
        // Interstitial AD request
        vponInterstitial.loadAd(interstitialRequest);
        // load interstitial
    }

    public void showBanner(View v){
        adBannerLayout = (RelativeLayout) findViewById(R.id.adLayout);
        vponBanner = new VpadnBanner(this,BannerbannerId,VpadnAdSize.SMART_BANNER,"TW");
        // create VpadnBanner instance
        // set banner options

        VpadnAdRequest bannerRequest = new VpadnAdRequest();
        // Banner Ad request
        bannerRequest.setEnableAutoRefresh(true);
        // if I change the update frequency, set true
        vponBanner.loadAd(bannerRequest);
        // load banner
        adBannerLayout.addView(vponBanner);
        // add banner to layout view
    }

    public void showInterstitial(View v){
        if (vponInterstitial.isReady()) {
            vponInterstitial.show();
            // if interstitial AD is ready, show AD

            /* VpadnAdRequest interstitialRequestAgain = new VpadnAdRequest();
            vponInterstitial.loadAd(interstitialRequestAgain); */
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (vponBanner != null) {
            vponBanner.destroy();
            vponBanner = null;
        }
        if (vponInterstitial != null) {
            vponInterstitial.destroy();
            vponInterstitial = null;
        }
    }

    @Override
    public void onVpadnReceiveAd(VpadnAd ad){
        if (ad == this.vponInterstitial){
            Log.d("Interstitial", "VpadnReceiveAd");
            btn_interstitial.setText("Interstitial AD Ready");
        }
    }

    @Override
    public void onVpadnDismissScreen(VpadnAd arg0){
        Log.d("Interstitial", "vpadnDismissScreen");
    }

    @Override
    public void onVpadnFailedToReceiveAd(VpadnAd arg0, VpadnAdRequest.VpadnErrorCode arg1){
        Log.d("Interstitial", "fail to receive ad (" + arg1 + ")");
    }

    @Override
    public void onVpadnLeaveApplication(VpadnAd arg0){
        Log.d("Interstitial", "VpadnLeaveApplication");
    }

    @Override
    public void onVpadnPresentScreen(VpadnAd arg0){
        Log.d("Interstitial", "VpadnPresentScreen");
    }

}