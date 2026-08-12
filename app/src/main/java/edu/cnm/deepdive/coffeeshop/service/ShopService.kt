package edu.cnm.deepdive.coffeeshop.service

import edu.cnm.deepdive.coffeeshop.model.domain.Shop
import edu.cnm.deepdive.coffeeshop.repository.FavoriteRepository
import edu.cnm.deepdive.coffeeshop.repository.ShopRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Singleton
class ShopService @Inject constructor(
    private val shopRepository: ShopRepository,
    private val favoriteRepository: FavoriteRepository
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun getShop(id: UUID) =
        scope.future {
            shopRepository.getShop(id)
                .apply {
                    isFavorite = id in favoriteRepository.getFavorites().map { it.id }
                }
        }

    fun getShops(): CompletableFuture<List<Shop>> =
        scope.future {
            val favoriteIds = favoriteRepository.getFavorites().map { it.id }.toSet()
            shopRepository.getShops().map { shop ->
                shop.apply {
                    isFavorite = id in favoriteIds
                }
            }
        }

    fun addFavorite(shop: Shop) =
        scope.future {
            favoriteRepository.addFavorite(shop)
        }

    fun removeFavorite(shop: Shop) =
        scope.future {
            favoriteRepository.removeFavorite(shop)
        }
}
