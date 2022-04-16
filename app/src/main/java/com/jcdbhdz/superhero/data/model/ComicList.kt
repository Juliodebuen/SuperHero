package com.jcdbhdz.superhero.data.model

data class ComicList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val item: ArrayList<ComicSummary>?
)
