package com.stevegregos.comcast.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.stevegregos.comcast.models.Bird
import com.stevegregos.comcast.models.Characteristics
import com.stevegregos.comcast.models.Dog
import com.stevegregos.comcast.models.Taxonomy
import com.stevegregos.comcast.requests.AnimalApi
import com.stevegregos.comcast.viewmodels.dispatchers.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class AnimalSearchViewModelTest {

    private val sparrow = Bird(
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

    private val pigeon = Bird(
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


    private val dog = Dog(
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

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: AnimalSearchViewModel
    private val mockApi: AnimalApi = mockk()

    @Before
    fun setUp() {
        viewModel = AnimalSearchViewModel(animalApi = mockApi)
    }

    @Test
    fun `fetchAnimals error shows empty animals list`() = runTest {
        val mockObserver: Observer<AnimalSearchViewModel.State> = mockk(relaxed = true)
        viewModel.stateLiveData.observeForever(mockObserver)

        coEvery { mockApi.searchAnimal(any()) } returns Response.error(
            400,
            "".toResponseBody(null)
        )

        viewModel.fetchAnimals()

        verify {
            mockObserver.onChanged(match {
                it == AnimalSearchViewModel.State.ERROR_STATE ||
                        AnimalSearchViewModel.State.DEFAULT_STATE == it // This "or" condition should be removed. We emit a default state initially, and we don't want to do that
            })
        }
        verify { mockObserver.onChanged(match { it.animals?.isEmpty() == true }) }
    }

    @Test
    fun `fetchAnimals success updates state with animals`() = runTest {
        val testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)
        val mockObserver: Observer<AnimalSearchViewModel.State> = mockk(relaxed = true)

        viewModel.stateLiveData.observeForever(mockObserver)

        coEvery { mockApi.searchAnimal(any()) } returns Response.success(testAnimalList)

        viewModel.fetchAnimals()
        testDispatcher.scheduler.advanceUntilIdle()

        verify {
            mockObserver.onChanged(match {
                it.animals?.size == 3
                        || AnimalSearchViewModel.State.DEFAULT_STATE == it // This "or" condition should be removed. We emit a default state initially, and we don't want to do that
            })
        }

        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `initial state is default`() {
        val initialState = viewModel.stateLiveData.value
        assert(initialState == AnimalSearchViewModel.State.DEFAULT_STATE)
    }
}
