package edu.cnm.deepdive.coffeeshop.service

import edu.cnm.deepdive.coffeeshop.model.domain.Profile
import edu.cnm.deepdive.coffeeshop.model.domain.Shop
import edu.cnm.deepdive.coffeeshop.repository.FavoriteRepository
import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future

@Singleton
class ProfileService @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val favoriteRepository: FavoriteRepository
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun getProfile() =
        scope.future {
            getProfileWithFavorites()
        }

    fun addFavorite(shop: Shop) =
        scope.future {
            favoriteRepository.addFavorite(shop)
            getProfileWithFavorites()
        }

    fun removeFavorite(shop: Shop) =
        scope.future {
            favoriteRepository.removeFavorite(shop)
            getProfileWithFavorites()
        }

    fun updateProfile(profile: Profile) =
        scope.future {
            profileRepository.updateProfile(profile).also {
                refreshFavorites(it)
            }
        }

    private suspend fun getProfileWithFavorites(): Profile =
        profileRepository.getProfile().also {
            refreshFavorites(it)
        }

    private suspend fun refreshFavorites(profile: Profile) {
        profile.favorites.clear()
        profile.favorites.addAll(favoriteRepository.getFavorites())
    }

}