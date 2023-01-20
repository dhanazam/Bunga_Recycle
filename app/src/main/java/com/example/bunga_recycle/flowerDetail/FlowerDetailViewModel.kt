package com.example.bunga_recycle.flowerDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bunga_recycle.data.DataSource
import com.example.bunga_recycle.data.Flower

class FlowerDetailViewModel(private val dataSource: DataSource): ViewModel() {
    fun getFlowerForId(id: Long): Flower? {
        return dataSource.getFlowerForId(id)
    }

    fun removeFlower(flower: Flower) {
        dataSource.removeFlower(flower)
    }
}

class FlowerDetailViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlowerDetailViewModel::class.java)) {
            return FlowerDetailViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}