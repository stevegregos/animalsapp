package com.stevegregos.comcast.models

import com.google.gson.annotations.SerializedName

open class Animal(
    @SerializedName("name") val name: String,
    @SerializedName("taxonomy") val taxonomy: Taxonomy,
    @SerializedName("locations") val locations: List<String>,
    @SerializedName("characteristics") val characteristics: Characteristics
) {

    companion object {
        val EMPTY = Animal(
            name = "",
            taxonomy = Taxonomy.EMPTY,
            locations = emptyList(),
            Characteristics.EMPTY
        )
    }
}

class Dog(
    name: String,
    taxonomy: Taxonomy,
    locations: List<String>,
    characteristics: Characteristics
) : Animal(name, taxonomy, locations, characteristics)

class Bug(
    name: String,
    taxonomy: Taxonomy,
    locations: List<String>,
    characteristics: Characteristics
) : Animal(name, taxonomy, locations, characteristics)

class Bird(
    name: String,
    taxonomy: Taxonomy,
    locations: List<String>,
    characteristics: Characteristics
) : Animal(name, taxonomy, locations, characteristics)