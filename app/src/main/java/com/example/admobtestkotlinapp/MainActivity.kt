package com.example.admobtestkotlinapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.admobtestkotlinapp.nativead.ActivityNativeAds
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),RewardedVideoAdListener {

    private lateinit var rewardedVideoAd:RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnIntertitialad.setOnClickListener {
            val intent=Intent(this,TestAdActivity::class.java)
            startActivity(intent)
        }

        btnNativeAd.setOnClickListener {
            var intent=Intent(this,NativeAdActivity::class.java)
            startActivity(intent)
        }

        btnNativeAds.setOnClickListener {
            val intent=Intent(this,ActivityNativeAds::class.java)
            startActivity(intent)
        }

        //rewarded video ads
        MobileAds.initialize(this,getString(R.string.sample_ad_mob_app_id))
        rewardedVideoAd=MobileAds.getRewardedVideoAdInstance(this)
        rewardedVideoAd.rewardedVideoAdListener=this
        loadAd()

        btnWatchVideo.setOnClickListener {
            if(rewardedVideoAd.isLoaded){
                rewardedVideoAd.show()
                btnWatchVideo.isEnabled=false
            }
        }

        //Banner ads
        val adRequest=AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener=object : AdListener(){
            override fun onAdImpression() {
                Log.d("TAG","banner ad impression")
                super.onAdImpression()
            }

            override fun onAdLeftApplication() {
                Log.d("TAG","banner ad left application")
                super.onAdLeftApplication()
            }

            override fun onAdClicked() {
                Log.d("TAG","banner ad on clicked")
                super.onAdClicked()
            }

            override fun onAdFailedToLoad(p0: Int) {
                Log.d("TAG","banner ad failed to load")
                super.onAdFailedToLoad(p0)
            }

            override fun onAdClosed() {
                Log.d("TAG","banner ad closed")
                super.onAdClosed()
            }

            override fun onAdOpened() {
                Log.d("TAG","banner ad opened")
                super.onAdOpened()
            }

            override fun onAdLoaded() {
                Log.d("TAG","banner ad loaded")
                super.onAdLoaded()
            }
        }
    }
    private fun loadAd(){
        rewardedVideoAd.loadAd(getString(R.string.test_rewarded_unit_id),AdRequest.Builder().build())
    }

    override fun onResume() {
        rewardedVideoAd.resume(this)
        super.onResume()
    }

    override fun onPause() {
        rewardedVideoAd.pause(this)
        super.onPause()
    }

    override fun onDestroy() {
        rewardedVideoAd.destroy(this)
        super.onDestroy()
    }

    override fun onRewardedVideoAdClosed() {
        Log.d("TAG","video ad closed")
        loadAd()
    }

    override fun onRewardedVideoAdLeftApplication() {
        Log.d("TAG","video ad left application")
    }

    override fun onRewardedVideoAdLoaded() {
        Log.d("TAG","video ad loaded")
        btnWatchVideo.isEnabled=true
    }

    override fun onRewardedVideoAdOpened() {
        Log.d("TAG","video ad opened")
    }

    override fun onRewardedVideoCompleted() {
        Log.d("TAG","video complete")
    }

    override fun onRewarded(p0: RewardItem?) {
        Log.d("TAG","${p0!!.amount}")
    }

    override fun onRewardedVideoStarted() {
        Log.d("TAG","video ad started")
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        Log.d("TAG","video ad failed to load")
    }
}
