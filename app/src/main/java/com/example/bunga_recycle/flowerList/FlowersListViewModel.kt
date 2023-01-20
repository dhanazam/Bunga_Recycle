package com.example.bunga_recycle.flowerList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bunga_recycle.data.DataSource
import com.example.bunga_recycle.data.Flower
import java.lang.IllegalArgumentException
import kotlin.random.Random

class FlowersListViewModel(val dataSource: DataSource) : ViewModel() {
    val flowersLiveData = dataSource.getFlowerList()

    fun insertFlower(flowerName: String?, flowerDescription: String?) {
        if (flowerName == null || flowerDescription == null) {
            return
        }

        val image = dataSource.getRandomFlowerImageAsset()
        val newFlower = Flower(
            Random.nextLong(),
            flowerName,
            image,
            flowerDescription
        )

        dataSource.addFlower(newFlower)
    }
}

class FlowersListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlowersListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlowersListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}