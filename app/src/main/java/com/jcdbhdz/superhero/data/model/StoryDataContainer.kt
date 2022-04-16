package com.jcdbhdz.superhero.data.model

data class StoryDataContainer(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<Story>?
)
