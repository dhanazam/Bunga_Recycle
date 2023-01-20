package com.example.bunga_recycle.flowerDetail

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bunga_recycle.R
import com.example.bunga_recycle.flowerList.FLOWER_ID

class FlowerDetailActivity: AppCompatActivity() {

    private val flowerDetailViewModel by viewModels<FlowerDetailViewModel> {
        FlowerDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flower_detail_activity)

        var currentFlowerId: Long? = null

        val flowerName: TextView = findViewById(R.id.flower_detail_name)
        val flowerImage: ImageView = findViewById(R.id.flower_detail_image)
        val flowerDescription: TextView = findViewById(R.id.flower_detail_description)
        val removeFlowerButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentFlowerId = bundle.getLong(FLOWER_ID)
        }

        currentFlowerId?.let {
            val currentFlower = flowerDetailViewModel.getFlowerForId(it)
            flowerName.text = currentFlower?.name
            if (currentFlower?.image == null) {
                flowerImage.setImageResource(R.drawable.rose)
            } else {
                flowerImage.setImageResource(currentFlower.image)
            }
            flowerDescription.text = currentFlower?.description

            removeFlowerButton.setOnClickListener {
                if (currentFlower != null) {
                    flowerDetailViewModel.removeFlower(currentFlower)
                }
                finish()
            }
        }
    }
}