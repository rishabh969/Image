package com.example.imageapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.imageapp.R
import com.example.imageapp.helper.ResponseItem
import com.example.imageapp.helper.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.image_fullscreen.view.*

class GalleryFullscreenFragment : DialogFragment() {

    private var imageList = ArrayList<ResponseItem>()
    private var selectedPosition: Int = 0
    lateinit var tvGalleryTitle: TextView
    lateinit var tvGalleryDetail:TextView
    lateinit var viewPager: ViewPager
    private lateinit var galleryPagerAdapter: GalleryPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery_fullscreen, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        tvGalleryTitle = view.findViewById(R.id.tvGalleryTitle)
        tvGalleryDetail = view.findViewById(R.id.tvGalleryDetail)
        imageList = arguments?.getSerializable("imagesTitle") as ArrayList<ResponseItem>
        selectedPosition = arguments?.getInt("position")!!
        galleryPagerAdapter = GalleryPagerAdapter()
        viewPager.adapter = galleryPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())

        setCurrentItem(selectedPosition)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    private fun setCurrentItem(position: Int) {
        viewPager.setCurrentItem(position, false)
        tvGalleryTitle.text = imageList[selectedPosition].title
        tvGalleryDetail.text=imageList[selectedPosition].explanation
    }

    // viewpager page change listener
    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
            object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            // set gallery title
            tvGalleryTitle.text = imageList[position].title
            tvGalleryDetail.text=imageList[position].explanation
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
        }

        override fun onPageScrollStateChanged(arg0: Int) {
        }
    }

    // gallery adapter
    inner class GalleryPagerAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.image_fullscreen, container, false)

            val image = imageList[position]

            // load image
            Glide.with(context!!)
                    .load(image.url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view.ivFullscreenImage)

            container.addView(view)

            return view
        }

        override fun getCount(): Int {
            return imageList.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj as View
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }
    }
}