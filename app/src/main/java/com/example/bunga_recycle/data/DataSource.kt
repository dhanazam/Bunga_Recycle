package com.example.bunga_recycle.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialFlowerList = flowerList(resources)
    private val flowersLiveData = MutableLiveData(initialFlowerList)

    fun getFlowerList(): LiveData<List<Flower>> {
        return flowersLiveData
    }

    fun addFlower(flower: Flower) {
        val currentList = flowersLiveData.value
        if (currentList == null) {
            flowersLiveData.postValue(listOf(flower))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    fun removeFlower(flower: Flower) {
        val currentList = flowersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    fun getFlowerForId(id: Long): Flower? {
        flowersLiveData.value?.let {
            return it.firstOrNull{ it.id == id }
        }
        return null
    }
    

    fun getRandomFlowerImageAsset(): Int? {
        val randomNumber = initialFlowerList.indices.random()
        return initialFlowerList[randomNumber].image
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class.java) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }

    }
}