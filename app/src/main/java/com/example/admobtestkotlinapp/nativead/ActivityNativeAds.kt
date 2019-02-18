package com.example.admobtestkotlinapp.nativead

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example.admobtestkotlinapp.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import kotlinx.android.synthetic.main.activity_native_ads.*

class ActivityNativeAds : AppCompatActivity() {

    private lateinit var adLoader: AdLoader
    lateinit var unifiedNativeAdView: UnifiedNativeAdView

    private var nativeAdList=ArrayList<UnifiedNativeAd>()
    private var mRecyclerViewItems=ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ads)
        progressBar.visibility= View.VISIBLE
        addMenuItem()
        MobileAds.initialize(this,getString(R.string.sample_ad_mob_app_id))
        loadNativeAd()
    }

    fun loadNativeAd(){

        adLoader=AdLoader.Builder(this,getString(R.string.test_native_unit_id))
            .forUnifiedNativeAd {  ad: UnifiedNativeAd ->
                nativeAdList.add(ad)
                if(!adLoader.isLoading) {
                    insertAdsInMenuItems()
                }
            }
            .withAdListener(object : AdListener(){
                override fun onAdFailedToLoad(p0: Int) {
                  if(!adLoader.isLoading){
                      insertAdsInMenuItems()
                  }
                }
                override fun onAdOpened() {
                    super.onAdOpened()
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                }
            })
            .build()
        adLoader.loadAds(AdRequest.Builder().build(),3)
    }
    fun insertAdsInMenuItems(){
        if(nativeAdList.size <=0){
            return
        }
        val offset=(mRecyclerViewItems.size /nativeAdList.size) +1
        var index=0
        for (ad:UnifiedNativeAd in nativeAdList){
            mRecyclerViewItems.add(index,ad)
            index += offset
        }
        setUpRecyclerView()
    }

    fun addMenuItem(){
        val menuItem=MenuItem("Fried Rice","fhdshj hdfjgfd hdfhghjg","$7.00","Dinner-salads",R.drawable.menu_item_image)
        val menuItem1=MenuItem("Grape Toast","fhdshj hdfjgfd hdfhghjg","$10.00","Dinner-salads",R.drawable.menu_item_image)
        mRecyclerViewItems.add(menuItem)
        mRecyclerViewItems.add(menuItem1)
        mRecyclerViewItems.add(menuItem)
        mRecyclerViewItems.add(menuItem1)
        mRecyclerViewItems.add(menuItem)
        mRecyclerViewItems.add(menuItem1)
        mRecyclerViewItems.add(menuItem)
        mRecyclerViewItems.add(menuItem1)
        mRecyclerViewItems.add(menuItem)
        mRecyclerViewItems.add(menuItem1)
        mRecyclerViewItems.add(menuItem)
        mRecyclerViewItems.add(menuItem1)
    }

    fun setUpRecyclerView(){
        progressBar.visibility=View.GONE
        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        val adapter=RecyclerViewAdapter(mRecyclerViewItems,this)
        recyclerView.adapter=adapter
    }
}
