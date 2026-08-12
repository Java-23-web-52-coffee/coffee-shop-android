package edu.cnm.deepdive.coffeeshop.service

import edu.cnm.deepdive.coffeeshop.model.domain.Visit
import edu.cnm.deepdive.coffeeshop.model.domain.Shop
import edu.cnm.deepdive.coffeeshop.repository.VisitDetailsRepository
import edu.cnm.deepdive.coffeeshop.repository.VisitRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Singleton
class VisitService @Inject constructor(
    private val visitRepository: VisitRepository,
    private val visitDetailsRepository: VisitDetailsRepository
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun getVisitsDetails(): CompletableFuture<List<Visit>> =
        scope.future {
            visitDetailsRepository.getVisitsDetails()
        }

    fun getVisitDetails(visitId: UUID): CompletableFuture<Visit> =
        scope.future {
            visitDetailsRepository.getVisitDetails(visitId)
        }

    fun createVisit(shop: Shop) =
        scope.future {
            visitRepository.createVisit(Visit(UUID.randomUUID(), shop))
        }

}
