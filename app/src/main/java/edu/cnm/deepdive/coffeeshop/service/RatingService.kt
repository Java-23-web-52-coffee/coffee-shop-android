package edu.cnm.deepdive.coffeeshop.service

import edu.cnm.deepdive.coffeeshop.model.domain.Interest
import edu.cnm.deepdive.coffeeshop.model.domain.Visit
import edu.cnm.deepdive.coffeeshop.model.dto.openapi.RatingDto
import edu.cnm.deepdive.coffeeshop.model.dto.openapi.RatingRequestDto
import edu.cnm.deepdive.coffeeshop.repository.RatingRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future

@Singleton
class RatingService @Inject constructor(private val ratingRepository: RatingRepository){

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    suspend fun addRating(visit: Visit, ratingRequestDto: RatingRequestDto) =
        scope.future {
            ratingRepository.addRating(visit, ratingRequestDto)
        }

    suspend fun removeRating(visit: Visit, interest: Interest) =
        scope.future {
            ratingRepository.removeRating(visit, interest)
        }

    suspend fun getRating(visit: Visit) =
        scope.future {
            ratingRepository.getRatings(visit)
        }

}