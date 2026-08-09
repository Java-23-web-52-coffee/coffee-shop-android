package edu.cnm.deepdive.coffeeshop.service

import edu.cnm.deepdive.coffeeshop.model.domain.Interest
import edu.cnm.deepdive.coffeeshop.model.domain.Preference
import edu.cnm.deepdive.coffeeshop.repository.PreferenceDetailsRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future
import java.math.BigDecimal
import java.util.concurrent.CompletableFuture

@Singleton
class PreferenceService @Inject constructor(private val preferenceDetailsRepository: PreferenceDetailsRepository) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    suspend fun getPreference(interestId: Long): CompletableFuture<List<Preference>> =
        scope.future {
            preferenceDetailsRepository.getPreferenceDetails(interestId)
            }

    suspend fun addPreference(interest: Interest, importance: BigDecimal) =
        scope.future {
            preferenceDetailsRepository.addPreferenceDetails(interest, importance)
        }

    suspend fun removePreference(interest: Interest) =
        scope.future {
            preferenceDetailsRepository.removePreferenceDetails(interest)
        }

    suspend fun updatePreference(interest: Interest, importance: BigDecimal) =
        scope.future {
            preferenceDetailsRepository.updatePreferenceDetails(interest, importance)
        }

}