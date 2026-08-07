package edu.cnm.deepdive.coffeeshop.service

import edu.cnm.deepdive.coffeeshop.repository.ShopRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class VisitService @Inject constructor(private val shopRepository: ShopRepository) {


}