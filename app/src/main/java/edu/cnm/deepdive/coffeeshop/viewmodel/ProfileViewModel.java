package edu.cnm.deepdive.coffeeshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.coffeeshop.model.domain.Profile;
import edu.cnm.deepdive.coffeeshop.model.domain.Shop;
import edu.cnm.deepdive.coffeeshop.service.ProfileService;
import jakarta.inject.Inject;

@HiltViewModel
public class ProfileViewModel extends ViewModel {

  private final ProfileService profileService;
  private final MutableLiveData<Profile> profile = new MutableLiveData<>();

  @Inject
  public ProfileViewModel(ProfileService profileService) {
    this.profileService = profileService;
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

  private void refreshProfile() {
    profileService.getProfile().whenComplete((updatedProfile, throwable) -> {
      if (throwable == null) {
        profile.postValue(updatedProfile);
      }
    });
  }

}
