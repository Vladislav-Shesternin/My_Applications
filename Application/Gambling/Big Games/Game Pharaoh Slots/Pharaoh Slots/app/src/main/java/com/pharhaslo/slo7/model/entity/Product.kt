package com.pharhaslo.slo7.model.entity

import com.google.gson.annotations.SerializedName

data class Product(


    @SerializedName("product_url")
    val productUrl : String,

    @SerializedName("product_img_big")
    val productImageBig : List<String>,

    @SerializedName("product_img_small")
    val productImageSmall : List<String>,
)