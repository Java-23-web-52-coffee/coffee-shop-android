package edu.cnm.deepdive.coffeeshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.cnm.deepdive.coffeeshop.model.domain.Shop
import edu.cnm.deepdive.coffeeshop.service.FavoriteService
import jakarta.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteService: FavoriteService) :
    ViewModel() {

    private val _favorites = MutableLiveData<List<Shop>>(emptyList())
    val favorites: LiveData<List<Shop>> = _favorites
    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error

    init {
        fetchFavorites()
    }

    fun fetchFavorites() {
        favoriteService.getFavorites()
            .thenAccept { _favorites.postValue(it) }
            .exceptionally {
                _error.postValue(it)
                null
            }
//            .whenComplete { shops, throwable ->
//            if (throwable != null) {
//                _error.postValue(throwable)
//            } else if (shops != null) {
//                _favorites.postValue(shops)
//            }
//        }
    }

    fun addFavorite(shop: Shop) {
        favoriteService.addFavorite(shop)
            .thenRun(::fetchFavorites)
            .exceptionally { throwable ->
                _error.postValue(throwable)
                null
            }
    }

    fun removeFavorite(shop: Shop) {
        favoriteService.removeFavorite(shop)
            .thenRun(::fetchFavorites)
            .exceptionally { throwable ->
                _error.postValue(throwable)
                null
            }
    }

}
