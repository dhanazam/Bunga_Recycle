package com.example.bunga_recycle.flowerList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bunga_recycle.R
import com.example.bunga_recycle.addFlower.AddFlowerActivity
import com.example.bunga_recycle.addFlower.FLOWER_DESCRIPTION
import com.example.bunga_recycle.addFlower.FLOWER_NAME
import com.example.bunga_recycle.data.Flower
import com.example.bunga_recycle.flowerDetail.FlowerDetailActivity

const val FLOWER_ID = "flower id"

class FlowersListActivity : AppCompatActivity() {
    private val newFlowerActivityRequestCode = 1
    private val flowersListViewModel by viewModels<FlowersListViewModel> {
        FlowersListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headerAdapter = HeaderAdapter()
        val flowersAdapter = FlowersAdapter { flower -> adapterOnClick(flower)}
        val concatAdapter = ConcatAdapter(headerAdapter, flowersAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = concatAdapter

        // Create the observer which updates the UI.
        val listFlowerObserver = Observer<List<Flower>> {
            it?.let {
                Log.d("listflower", it.toString())
                flowersAdapter.submitList(it as MutableList<Flower>)
                headerAdapter.updateFlowerCount(it.size)
            }
        }

        flowersListViewModel.flowersLiveData.observe(this, listFlowerObserver)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }

    }

    private fun adapterOnClick(flower: Flower) {
        val intent = Intent(this, FlowerDetailActivity::class.java)
        intent.putExtra(FLOWER_ID, flower.id)
        startActivity(intent)
    }

    /* Adds flower to flowerList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddFlowerActivity::class.java)
        addFlowerResult.launch(Intent(this, AddFlowerActivity::class.java))
    }

    val addFlowerResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.let {
                val flowerName = it.getStringExtra(FLOWER_NAME)
                val flowerDescription = it.getStringExtra(FLOWER_DESCRIPTION)

                flowersListViewModel.insertFlower(flowerName, flowerDescription)
            }
        }
    }
}