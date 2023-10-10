package com.stevegregos.comcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevegregos.comcast.models.Animal
import com.stevegregos.comcast.models.Bird
import com.stevegregos.comcast.models.Bug
import com.stevegregos.comcast.models.Dog
import com.stevegregos.comcast.viewmodels.AnimalSearchViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: AnimalSearchViewModel = viewModel()) {
    var searchText by remember { mutableStateOf("") }
    val animalsState = viewModel.stateLiveData.observeAsState() // This initial observer causes the erroneous default emission

    Column {
        SearchBar(text = searchText, onTextChange = { newText ->
            searchText = newText
        })

        Spacer(modifier = Modifier.height(8.dp))

        val filteredAnimals = animalsState.value?.animals?.filter {
            it.name.contains(searchText, ignoreCase = true) ||
                    it.characteristics.commonName?.contains(
                        searchText,
                        ignoreCase = true
                    ) == true
        }

        if (!filteredAnimals.isNullOrEmpty()) {
            AnimalsList(animals = filteredAnimals)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(text: String, onTextChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = "Search Animals") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun AnimalsList(animals: List<Animal>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(animals) { animal ->
            AnimalItem(animal)
            Divider()
        }
    }
}

@Composable
fun AnimalItem(animal: Animal) {
    /**
     * This composable would ideally change to use typed responses parsed directly at the networking layer for the data classes Dog, Bird, and Bug.
     *
     * Here, we're simply identifying each item the same way that the AnimalAPI API does: by searching animal's names for "dog", "bird", and "bug".
     */
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${animal.name}")
            Text(text = "Phylum: ${animal.taxonomy.phylum}")
            Text(text = "Scientific Name: ${animal.taxonomy.scientificName}")

            if (animal.name.contains("dog")) {
                Text(text = "Slogan: ${animal.characteristics.slogan}")
                Text(text = "Lifespan: ${animal.characteristics.lifespan}")
            } else if(animal.name.contains("bird")) {
                Text(text = "Habitat: ${animal.characteristics.habitat}")
            } else if(animal.name.contains("bug")) {
                Text(text = "Prey: ${animal.characteristics.prey}")
                Text(text = "Predators: ${animal.characteristics.predators}")
        }

//            when (animal) {
//                is Dog -> {
//                    Text(text = "Slogan: ${animal.characteristics.slogan}")
//                    Text(text = "Lifespan: ${animal.characteristics.lifespan}")
//                }
//
//                is Bird -> {
//                    Text(text = "Habitat: ${animal.characteristics.habitat}")
//                }
//
//                is Bug -> {
//                    Text(text = "Prey: ${animal.characteristics.prey}")
//                    Text(text = "Predators: ${animal.characteristics.predators}")
//                }
//            }
        }
    }
}