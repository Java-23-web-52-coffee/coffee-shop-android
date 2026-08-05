package edu.cnm.deepdive.coffeeshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.coffeeshop.model.dto.ProfileDto;
import edu.cnm.deepdive.coffeeshop.repository.AuthRepository;
import edu.cnm.deepdive.coffeeshop.repository.AuthRepository.ResultCallback;
import javax.inject.Inject;

/**
 * Shared by {@code SignInFragment} and {@code SignUpFragment} — both screens only differ in
 * which {@link AuthRepository} call they trigger, and expose the same loading/error/success state.
 */
@HiltViewModel
public class AuthViewModel extends ViewModel {

  private final AuthRepository authRepository;
  private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
  private final MutableLiveData<ProfileDto> signedInProfile = new MutableLiveData<>();
  private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

  @Inject
  public AuthViewModel(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  public LiveData<Boolean> getLoading() {
    return loading;
  }

  public LiveData<ProfileDto> getSignedInProfile() {
    return signedInProfile;
  }

  public LiveData<String> getErrorMessage() {
    return errorMessage;
  }

  public void signIn(String email, String password) {
    loading.setValue(true);
    authRepository.signIn(email, password, new ResultCallback<>() {
      @Override
      public void onSuccess(ProfileDto result) {
        loading.setValue(false);
        signedInProfile.setValue(result);
      }

      @Override
      public void onError(String message) {
        loading.setValue(false);
        errorMessage.setValue(message);
      }
    });
  }

  public void signUp(String name, String email, String password, String passwordConfirm) {
    loading.setValue(true);
    authRepository.signUp(name, email, password, passwordConfirm, new ResultCallback<>() {
      @Override
      public void onSuccess(ProfileDto result) {
        loading.setValue(false);
        signedInProfile.setValue(result);
      }

      @Override
      public void onError(String message) {
        loading.setValue(false);
        errorMessage.setValue(message);
      }
    });
  }

  public void signOut() {
    authRepository.signOut(new ResultCallback<>() {
      @Override
      public void onSuccess(Void result) {
        signedInProfile.setValue(null);
      }

      @Override
      public void onError(String message) {
        errorMessage.setValue(message);
      }
    });
  }

}
