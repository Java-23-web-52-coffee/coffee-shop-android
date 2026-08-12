package edu.cnm.deepdive.coffeeshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.util.Log;
import dagger.hilt.android.lifecycle.HiltViewModel;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.coffeeshop.model.domain.Profile;
import edu.cnm.deepdive.coffeeshop.model.domain.Shop;
import edu.cnm.deepdive.coffeeshop.service.ProfileService;
import edu.cnm.deepdive.coffeeshop.service.VisitService;
import jakarta.inject.Inject;

@HiltViewModel
public class ProfileViewModel extends ViewModel {

  private static final String TAG = ProfileViewModel.class.getSimpleName();
  private final ProfileService profileService;
  private final VisitService visitService;
  private final MutableLiveData<Profile> profile = new MutableLiveData<>();



  @Inject
  public ProfileViewModel(ProfileService profileService, VisitService visitService) {
    this.profileService = profileService;
    this.visitService = visitService;
    refreshProfile();
  }

  public LiveData<Profile> getProfile() {
    return profile;
  }

  public void setFavorite(Shop shop, boolean isFavorite) {
    (isFavorite ? profileService.addFavorite(shop) : profileService.removeFavorite(shop))
        .whenComplete((updatedProfile, throwable) -> {
          if (throwable == null) {
            profile.postValue(updatedProfile);
          }
        });
  }

  public void setVisited(Shop shop) {
    visitService.createVisit(shop).whenComplete((ignored, throwable) -> {
      if (throwable == null) {
        refreshProfile();
      } else {
        Log.e(TAG, "Unable to save visited shop", throwable);
      }
    });
  }

  private void refreshProfile() {
    profileService.getProfile().whenComplete((updatedProfile, throwable) -> {
      if (throwable == null) {
        profile.postValue(updatedProfile);
      } else {
        Log.e(TAG, "Unable to load profile", throwable);
      }
    });
  }

}

