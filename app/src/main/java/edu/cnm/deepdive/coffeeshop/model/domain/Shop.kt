package edu.cnm.deepdive.coffeeshop.model.domain

import java.net.URI
import java.util.UUID

data class Shop @JvmOverloads constructor(
    val id: UUID,
    var name: String = "",
    val address: String?= null,
    val hours: Any? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    val phone: String? = null,
    val imageUrl: URI? = null
)

fun Shop.getName(): String = name

fun Shop.setName(name: String) {
    this.name = name
}
