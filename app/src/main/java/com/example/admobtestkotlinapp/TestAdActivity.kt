package com.example.admobtestkotlinapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_test_ad.*

class TestAdActivity : AppCompatActivity() {
    private lateinit var mInterstitialAd:InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_ad)

        mInterstitialAd= InterstitialAd(this)
        mInterstitialAd.adUnitId=getString(R.string.test_interstitial_unit_id)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        btnIntertitialad.setOnClickListener {
            if(mInterstitialAd.isLoaded){
                mInterstitialAd.show()
            }
        }
        mInterstitialAd.adListener=object:AdListener(){
            override fun onAdClosed() {
                mInterstitialAd.loadAd(AdRequest.Builder().build())
            }
        }
    }
}
