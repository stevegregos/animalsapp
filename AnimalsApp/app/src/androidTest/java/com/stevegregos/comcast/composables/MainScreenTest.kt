package com.stevegregos.comcast.composables

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.stevegregos.comcast.MainActivity
import com.stevegregos.comcast.models.Animal
import com.stevegregos.comcast.models.Characteristics
import com.stevegregos.comcast.models.Taxonomy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testSearchBarDisplay() {
        composeTestRule.onNodeWithText("Search Animals").assertExists()
    }

    /**
     * This test would call for mocking the ViewModel, passing a list of <Animal> objects like the list below, and testing that the list does the following;
     * 1. Entering 'sparrow' in the search box correctly filters and UI updates to a sparrow animal
     * 2. Entering 'pigeon' in the search box correctly filters and UI updates to a pigeon animal
     * 3. In both of the above situations, check that 'dog' is not displayed
     */

    private val sparrow = Animal(
        name = "sparrow",
        taxonomy = Taxonomy(
            kingdom = "Animalia",
            phylum = "Chordata",
            clazz = "Aves", // Birds class
            order = "Passeriformes",
            family = "Passeridae",
            genus = "Passer",
            scientificName = "Passer domesticus"
        ),
        locations = listOf("North America", "Europe", "Asia"),
        characteristics = Characteristics.EMPTY
    )

    private val pigeon = Animal(
        name = "pigeon",
        taxonomy = Taxonomy(
            kingdom = "Animalia",
            phylum = "Chordata",
            clazz = "Aves", // Birds class
            order = "Columbiformes",
            family = "Columbidae",
            genus = "Columba",
            scientificName = "Columba livia"
        ),
        locations = listOf("Worldwide"),
        characteristics = Characteristics.EMPTY
    )


    private val dog = Animal(
        name = "dog",
        taxonomy = Taxonomy(
            kingdom = "Animalia",
            phylum = "Chordata",
            clazz = "Mammalia",
            order = "Carnivora",
            family = "Canidae",
            genus = "Canis",
            scientificName = "Canis lupus familiaris"
        ),
        locations = listOf("Worldwide"),
        characteristics = Characteristics.EMPTY
    )

    private val testAnimalList = listOf(sparrow, pigeon, dog)
}