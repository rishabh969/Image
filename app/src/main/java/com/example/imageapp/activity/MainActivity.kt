package com.example.imageapp.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imageapp.R
import com.example.imageapp.adapter.GalleryImageAdapter
import com.example.imageapp.adapter.GalleryImageClickListener
import com.example.imageapp.fragment.GalleryFullscreenFragment
import com.example.imageapp.helper.ResponseItem
import com.example.imageapp.repository.DataModification
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GalleryImageClickListener {

    // gallery column count
    private val spanCount = 2
    private lateinit var galleryAdapter: GalleryImageAdapter
    private lateinit var dataList: ArrayList<ResponseItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        dataList =mainViewModel.getProjectList(applicationContext)//DataModification().fetchData(applicationContext)
        // init adapter
        galleryAdapter = GalleryImageAdapter(dataList)
        galleryAdapter.listener = this

        // init recyclerview
        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        recyclerView.adapter = galleryAdapter

    }

    override fun onClick(position: Int) {
        // handle click of image
        val bundle = Bundle()
        bundle.putSerializable("imagesTitle", dataList/*imageList*/)
        bundle.putInt("position", position)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val galleryFragment = GalleryFullscreenFragment()
        galleryFragment.arguments = bundle
        galleryFragment.show(fragmentTransaction, "gallery")
    }
}
