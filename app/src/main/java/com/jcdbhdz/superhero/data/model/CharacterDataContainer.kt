package com.jcdbhdz.superhero.data.model

data class CharacterDataContainer(
    val offset:Int?,
    val limit:Int?,
    val total:Int?,
    val count:Int?,
    val results:ArrayList<Character>?,
)