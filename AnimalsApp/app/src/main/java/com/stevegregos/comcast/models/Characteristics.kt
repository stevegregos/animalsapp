package com.stevegregos.comcast.models

import com.google.gson.annotations.SerializedName

// Some Characteristic fields in the API response can be blank (nullable) - fields are marked accordingly
data class Characteristics(
    @SerializedName("prey") val prey: String?,
    @SerializedName("name_of_young") val nameOfYoung: String?,
    @SerializedName("group_behavior") val groupBehavior: String?,
    @SerializedName("estimated_population_size") val estimatedPopulationSize: String?,
    @SerializedName("biggest_threat") val biggestThreat: String?,
    @SerializedName("most_distinctive_feature") val mostDistinctiveFeature: String?,
    @SerializedName("other_name(s)") val otherNames: String?,
    @SerializedName("gestation_period") val gestationPeriod: String?,
    @SerializedName("habitat") val habitat: String?,
    @SerializedName("predators") val predators: String?,
    @SerializedName("diet") val diet: String?,
    @SerializedName("average_litter_size") val averageLitterSize: String?,
    @SerializedName("lifestyle") val lifestyle: String?,
    @SerializedName("common_name") val commonName: String?,
    @SerializedName("number_of_species") val numberOfSpecies: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("slogan") val slogan: String?,
    @SerializedName("group") val group: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("skin_type") val skinType: String?,
    @SerializedName("top_speed") val topSpeed: String?,
    @SerializedName("lifespan") val lifespan: String?,
    @SerializedName("weight") val weight: String?,
    @SerializedName("length") val length: String?,
    @SerializedName("age_of_sexual_maturity") val ageOfSexualMaturity: String?,
    @SerializedName("age_of_weaning") val ageOfWeaning: String?
) {
    companion object {
        val EMPTY = Characteristics(
            prey = null,
            nameOfYoung = null,
            groupBehavior = null,
            estimatedPopulationSize = null,
            biggestThreat = null,
            mostDistinctiveFeature = null,
            otherNames = null,
            gestationPeriod = null,
            habitat = null,
            predators = null,
            diet = null,
            averageLitterSize = null,
            lifestyle = null,
            commonName = null,
            numberOfSpecies = null,
            location = null,
            slogan = null,
            group = null,
            color = null,
            skinType = null,
            topSpeed = null,
            lifespan = null,
            weight = null,
            length = null,
            ageOfSexualMaturity = null,
            ageOfWeaning = null
        )
    }
}