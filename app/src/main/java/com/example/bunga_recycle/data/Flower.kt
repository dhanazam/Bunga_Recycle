package com.example.bunga_recycle.data

import androidx.annotation.DrawableRes

data class Flower(
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int?,
    val description: String
)
