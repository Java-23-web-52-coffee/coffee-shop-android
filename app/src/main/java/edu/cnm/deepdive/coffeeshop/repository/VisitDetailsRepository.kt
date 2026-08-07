package edu.cnm.deepdive.coffeeshop.repository

import edu.cnm.deepdive.coffeeshop.model.domain.Visit
import edu.cnm.deepdive.coffeeshop.model.dto.openapi.VisitDto
import edu.cnm.deepdive.coffeeshop.service.openapi.RatingApi
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

@Singleton
class VisitDetailsRepository @Inject constructor(
    private val visitRepository: VisitRepository,
    private val shopRepository: ShopRepository,
    private val ratingApi: RatingApi
) {

suspend fun getVisitDetails(visitId: UUID): Visit {

}

    private fun VisitDto.toVisit(): Visit {
        return Visit(
            id = id,
            shop = shopRepository.getShop(id),
            rating = ratingApi.getRating(id),
        )
    }
}