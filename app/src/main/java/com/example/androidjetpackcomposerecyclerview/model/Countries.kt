package com.example.androidjetpackcomposerecyclerview.model

import androidx.annotation.DrawableRes

data class Countries(
    var CountryName: String,
    var Capital: String,
    var Continent: String,
    @DrawableRes var photoFlag: Int
)
