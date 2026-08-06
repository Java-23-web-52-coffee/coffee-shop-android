package edu.cnm.deepdive.coffeeshop.repository

import edu.cnm.deepdive.coffeeshop.model.domain.Shop
import edu.cnm.deepdive.coffeeshop.model.dto.openapi.ShopDto
import edu.cnm.deepdive.coffeeshop.service.openapi.ShopApi
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class ShopRepository @Inject constructor(private val shopApi: ShopApi) {

    suspend fun getShop(id: UUID): Shop {
        return shopApi.getShopById(id).let {
            if (it.isSuccessful) {
                it.body()?.toShop() ?: throw ServiceException(
                    it.errorBody()?.string() ?: "Unknown error"
                )
            } else {
                throw ServiceException(it.errorBody()?.string() ?: "Unknown error")
            }
        }
    }

    suspend fun getShops(): List<Shop> {
        val response = shopApi.listShops()
        return if (response.isSuccessful) {
            response.body()?.map { it.toShop() } ?: emptyList()
        } else {
            throw ServiceException(response.errorBody()?.string() ?: "Unknown error")
        }
    }
}

private fun ShopDto.toShop(): Shop {
    return Shop(
        id = this.id,
        name = this.name,
        address = this.address,
        hours = this.hours,
        lat = this.lat,
        lng = this.lng,
        phone = this.phone,
        imageUrl = this.imageUrl,
    )

}