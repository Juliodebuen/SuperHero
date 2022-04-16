package com.jcdbhdz.superhero.data.model

data class SeriesDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: SeriesDataContainer?,
    val etag: String?,
)
