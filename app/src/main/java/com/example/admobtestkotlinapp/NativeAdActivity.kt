package com.example.admobtestkotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.formats.NativeAd

import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_native_ad.*

class NativeAdActivity : AppCompatActivity() {


    private lateinit var adLoader: AdLoader
    lateinit var nativeAd:UnifiedNativeAd
    lateinit var unifiedNativeAdView:UnifiedNativeAdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad)
        progressBar.visibility=View.VISIBLE

        unifiedNativeAdView=ad_view
        MobileAds.initialize(this,getString(R.string.sample_ad_mob_app_id))

        adLoader=AdLoader.Builder(this,getString(R.string.test_native_unit_id))
            .forUnifiedNativeAd {  ad:UnifiedNativeAd->
                if(!adLoader.isLoading) {
                    nativeAd = ad
                    setNativeAdViewData(nativeAd,unifiedNativeAdView)
                }
            }
            .withAdListener(object :AdListener(){
                override fun onAdFailedToLoad(p0: Int) {
                    super.onAdFailedToLoad(p0)
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                }
            })
            .build()
            adLoader.loadAd(AdRequest.Builder().build())

            // we also make request for multiple native ads its max limit is 5
            // adLoader.loadAds(AdRequest.Builder().build(),3)
    }
    private fun setNativeAdViewData(nativeAd:UnifiedNativeAd,adView: UnifiedNativeAdView){
            adView.mediaView=ad_media
            adView.headlineView=ad_headline
            adView.bodyView=ad_body
            adView.callToActionView=ad_call_to_action
            adView.iconView=ad_icon
            adView.priceView=ad_price
            adView.starRatingView=ad_stars
            adView.storeView=ad_store
            adView.advertiserView=ad_advertiser

        (adView.headlineView as TextView).text=nativeAd.headline
        (adView.bodyView as TextView).text=nativeAd.body
        (adView.callToActionView as Button).text=nativeAd.callToAction

        val icon=nativeAd.icon

        if(icon==null){
            adView.iconView.visibility= View.INVISIBLE
        }else{
            (adView.iconView as ImageView).setImageDrawable(icon.drawable)
            adView.iconView.visibility= View.VISIBLE
        }

        if(nativeAd.price==null){
            adView.priceView.visibility=View.INVISIBLE
        }else{
            adView.priceView.visibility=View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }

        if(nativeAd.store==null){
            adView.storeView.visibility=View.INVISIBLE
        }else{
            adView.storeView.visibility=View.VISIBLE
            (adView.storeView as TextView).text=nativeAd.store
        }

        if(nativeAd.starRating==null){
            adView.starRatingView.visibility=View.INVISIBLE
        }else{
            adView.starRatingView.visibility=View.VISIBLE
            (adView.starRatingView as RatingBar).rating=nativeAd.starRating.toFloat()
        }

        if(nativeAd.advertiser==null){
            adView.advertiserView.visibility=View.INVISIBLE
        }else{
            adView.advertiserView.visibility=View.VISIBLE
            (adView.advertiserView as TextView).text=nativeAd.advertiser
        }

        adView.setNativeAd(nativeAd)
        progressBar.visibility=View.GONE
        card_ad_view.visibility=View.VISIBLE
    }
}
