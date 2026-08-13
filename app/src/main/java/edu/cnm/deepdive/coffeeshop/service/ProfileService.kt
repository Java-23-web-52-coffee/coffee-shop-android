package edu.cnm.deepdive.coffeeshop.service

import edu.cnm.deepdive.coffeeshop.model.domain.Profile
import edu.cnm.deepdive.coffeeshop.model.domain.Shop
import edu.cnm.deepdive.coffeeshop.repository.FavoriteRepository
import edu.cnm.deepdive.coffeeshop.repository.PreferenceDetailsRepository
import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository
import edu.cnm.deepdive.coffeeshop.repository.VisitDetailsRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future

@Singleton
class ProfileService @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val favoriteRepository: FavoriteRepository,
    private val preferenceDetailsRepository: PreferenceDetailsRepository,
    private val visitDetailsRepository: VisitDetailsRepository
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun getProfile() =
        scope.future {
            getExtendedProfile()
        }

    fun addFavorite(shop: Shop) =
        scope.future {
            favoriteRepository.addFavorite(shop)
            getExtendedProfile()
        }

    fun removeFavorite(shop: Shop) =
        scope.future {
            favoriteRepository.removeFavorite(shop)
            getExtendedProfile()
        }

    fun updateProfile(profile: Profile) =
        scope.future {
            profileRepository.updateProfile(profile).also {
                refreshFavorites(it)
            }
        }

    private suspend fun getExtendedProfile(): Profile =
        profileRepository.getProfile().also {
            refreshFavorites(it)
            refreshPreferences(it)
            refreshVisits(it)
        }

    private suspend fun refreshFavorites(profile: Profile) {
        profile.favorites.clear()
        profile.favorites.addAll(favoriteRepository.getFavorites())
    }

    private suspend fun refreshPreferences(profile: Profile) {
        profile.preferences.clear()
        profile.preferences.addAll(preferenceDetailsRepository.getPreferenceDetails())
    }

    private suspend fun refreshVisits(profile: Profile) {
        profile.visits.clear()
        profile.visits.addAll(visitDetailsRepository.getVisitsDetails())
    }

}
