package edu.cnm.deepdive.coffeeshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.cnm.deepdive.coffeeshop.model.domain.Shop
import edu.cnm.deepdive.coffeeshop.model.domain.Visit
import edu.cnm.deepdive.coffeeshop.service.VisitService
import jakarta.inject.Inject
import java.util.UUID

@HiltViewModel
class VisitsViewModel @Inject constructor(private val visitService: VisitService) : ViewModel() {

    private val _visits = MutableLiveData<List<Visit>>()
    val visits: LiveData<List<Visit>> = _visits

    init {
        fetchVisits()
    }

    fun fetchVisits() {
        // Mock visits locally inside the ViewModel
        _visits.value = buildTestVisits()
    }

    private fun buildTestVisits(): List<Visit> {
        val shop1 = Shop(
            id = UUID.randomUUID(),
            name = "Little Bear Coffee",
            address = "2632 Pennsylvania St NE",
            hours = "7:00 AM - 5:00 PM",
            lat = 35.0880,
            lng = -106.6510,
            phone = "505-555-0100",
            imageUrl = null,
            isFavorite = true
        )

        val shop2 = Shop(
            id = UUID.randomUUID(),
            name = "Espresso Express",
            address = "123 Main St",
            hours = "6:30 AM - 4:00 PM",
            lat = 35.0850,
            lng = -106.6500,
            phone = "505-555-0102",
            imageUrl = null,
            isFavorite = false
        )

        return listOf(
            Visit(id = UUID.randomUUID(), shop = shop1),
            Visit(id = UUID.randomUUID(), shop = shop2)
        )
    }

}