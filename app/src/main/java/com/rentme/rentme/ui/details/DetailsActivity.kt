package com.rentme.rentme.ui.details

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.google.android.material.tabs.TabLayout
import com.rentme.rentme.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.adapter.DetailPhotoAdapter
import com.rentme.rentme.databinding.ActivityDetailsBinding
import com.rentme.rentme.model.DetailPhoto
import com.rentme.rentme.ui.location.SelectLocationActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val adapter by lazy { DetailPhotoAdapter() }
    private var photoList = ArrayList<DetailPhoto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)

        initViews()
        getAllDetailPhoro()
        tabLayoutManager()
    }

    private fun initViews() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvDetailImages)
        binding.rvDetailImages.adapter = adapter


        binding.ivBackToDetails.setOnClickListener {
            finish()
        }

        binding.llLocationDetails.setOnClickListener {
            intent = Intent(this, SelectLocationActivity::class.java)
            startActivity(intent)
        }


    }

    private fun tabLayoutManager() {
        binding.apply {
            if (photoList.size == 1) {
                tabLayout.isVisible = false
            }

            for (category in 0 until photoList.size) {
                tabLayout.addTab(binding.tabLayout.newTab())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                else tabLayout.getTabAt(category)?.text = "⬤"
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_circle_select)

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    rvDetailImages.smoothScrollToPosition(tab!!.position)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                        allTabLayoutIndicatorUnselected()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                        tabLayout.getTabAt(tab.position)?.setIcon(R.drawable.ic_circle_select)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })


            rvDetailImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    Log.d("TAGS", "onScrollStateChanged: ok")
                    val layoutManager =
                        LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                    val lastVisible = layoutManager!!.findLastVisibleItemPosition()
                    Log.d("TAGS", "onScrollStateChanged: $lastVisible")
                    tabLayout.setScrollPosition(lastVisible, 0f, true)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                        allTabLayoutIndicatorUnselected()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                        tabLayout.getTabAt(lastVisible)?.setIcon(R.drawable.ic_circle_select)
                }
            })

            TabbedListMediator(
                rvDetailImages,
                tabLayout,
                photoList.indices.toList(),
                true
            )
        }
    }

    private fun allTabLayoutIndicatorUnselected() {
        for (category in 0 until photoList.size) {
            binding.tabLayout.getTabAt(category)?.setIcon(R.drawable.ic_circle_unselect)
        }
    }

    private fun getAllDetailPhoro(){
        val items:ArrayList<DetailPhoto> = ArrayList()
        items.add(DetailPhoto(R.drawable.im_tesla_model3))
        items.add(DetailPhoto(R.drawable.im_mersades))
        items.add(DetailPhoto(R.drawable.im_malibu))
        items.add(DetailPhoto(R.drawable.im_tesla_model3))

        photoList.addAll(items)
        adapter.sumbitData(photoList)
    }
}