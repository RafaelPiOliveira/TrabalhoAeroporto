package com.example.trabalhoaeroporto.api

import com.google.gson.annotations.SerializedName

data class VooResponse(
    @SerializedName("pagination")
    val pagination: Pagination?,

    @SerializedName("data")
    val data: List<Voo>
)

data class Pagination(
    @SerializedName("limit")
    val limit: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("count")
    val count: Int,

    @SerializedName("total")
    val total: Int
)