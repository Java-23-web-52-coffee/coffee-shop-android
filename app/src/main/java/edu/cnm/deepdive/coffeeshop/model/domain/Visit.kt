package edu.cnm.deepdive.coffeeshop.model.domain

import java.time.OffsetDateTime

data class Visit(
    val shop: Shop,
    val date: OffsetDateTime = OffsetDateTime.now(),
    val ratings: MutableList<Rating> = mutableListOf()
)
