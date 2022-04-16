package com.jcdbhdz.superhero.data.model

data class EventDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: EventDataContainer?,
    val etag: String?,
)
