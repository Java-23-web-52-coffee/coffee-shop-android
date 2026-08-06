package edu.cnm.deepdive.coffeeshop.repository

import edu.cnm.deepdive.coffeeshop.model.domain.Visit
import edu.cnm.deepdive.coffeeshop.model.dto.openapi.VisitDto
import edu.cnm.deepdive.coffeeshop.service.openapi.VisitApi
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class VisitRepository @Inject constructor(private val visitApi: VisitApi) {

    suspend fun createVisit(visit: Visit): VisitDto? {
        return visitApi.createVisit(visit).body()
    }
}