package com.stevegregos.comcast.models

import com.google.gson.annotations.SerializedName

data class Taxonomy(
    @SerializedName("kingdom") val kingdom: String,
    @SerializedName("phylum") val phylum: String,
    @SerializedName("class") val clazz: String, //class is reserved
    @SerializedName("order") val order: String,
    @SerializedName("family") val family: String,
    @SerializedName("genus") val genus: String,
    @SerializedName("scientific_name") val scientificName: String
) {
    companion object {
        val EMPTY = Taxonomy(
            kingdom = "",
            phylum = "",
            clazz = "",
            order = "",
            family = "",
            genus = "",
            scientificName = ""
        )
    }
}