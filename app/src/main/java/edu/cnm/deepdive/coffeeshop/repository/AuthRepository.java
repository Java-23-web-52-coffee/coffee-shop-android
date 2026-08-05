package edu.cnm.deepdive.coffeeshop.repository;

import com.squareup.moshi.Moshi;
import edu.cnm.deepdive.coffeeshop.model.dto.ErrorResponseDto;
import edu.cnm.deepdive.coffeeshop.model.dto.ProfileDto;
import edu.cnm.deepdive.coffeeshop.model.dto.SignInRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.SignUpRequestDto;
import edu.cnm.deepdive.coffeeshop.service.AuthApiService;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Wraps {@link AuthApiService} and {@link SessionManager}: performs the sign-up/sign-in/sign-out
 * network calls and persists (or clears) the resulting session.
 */
@Singleton
public class AuthRepository {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_PREFIX = "Bearer ";
  private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong. Please try again.";

  private final AuthApiService authApiService;
  private final SessionManager sessionManager;
  private final Moshi moshi;

  @Inject
  public AuthRepository(AuthApiService authApiService, SessionManager sessionManager,
      Moshi moshi) {
    this.authApiService = authApiService;
    this.sessionManager = sessionManager;
    this.moshi = moshi;
  }

  public void signIn(String email, String password, ResultCallback<ProfileDto> callback) {
    authApiService
        .signIn(new SignInRequestDto(email, password))
        .enqueue(new retrofit2.Callback<>() {
          @Override
          public void onResponse(Call<ProfileDto> call, Response<ProfileDto> response) {
            ProfileDto profile = response.body();
            if (response.isSuccessful() && profile != null) {
              sessionManager.saveSession(extractToken(response.headers()), profile);
              callback.onSuccess(profile);
            } else {
              callback.onError(errorMessage(response));
            }
          }

          @Override
          public void onFailure(Call<ProfileDto> call, Throwable throwable) {
            callback.onError(DEFAULT_ERROR_MESSAGE);
          }
        });
  }

  public void signUp(String name, String email, String password, String passwordConfirm,
      ResultCallback<ProfileDto> callback) {
    authApiService
        .signUp(new SignUpRequestDto(name, email, password, passwordConfirm))
        .enqueue(new retrofit2.Callback<>() {
          @Override
          public void onResponse(Call<ProfileDto> call, Response<ProfileDto> response) {
            if (response.isSuccessful()) {
              // Sign-up returns no token; sign in immediately with the same credentials.
              signIn(email, password, callback);
            } else {
              callback.onError(errorMessage(response));
            }
          }

          @Override
          public void onFailure(Call<ProfileDto> call, Throwable throwable) {
            callback.onError(DEFAULT_ERROR_MESSAGE);
          }
        });
  }

  public void signOut(ResultCallback<Void> callback) {
    authApiService
        .signOut()
        .enqueue(new retrofit2.Callback<>() {
          @Override
          public void onResponse(Call<Void> call, Response<Void> response) {
            sessionManager.clearSession();
            callback.onSuccess(null);
          }

          @Override
          public void onFailure(Call<Void> call, Throwable throwable) {
            // Sign-out is really a client-side "forget the token" operation (the server side is
            // currently a no-op), so clear locally regardless of a network hiccup.
            sessionManager.clearSession();
            callback.onSuccess(null);
          }
        });
  }

  private static String extractToken(Headers headers) {
    String header = headers.get(AUTHORIZATION_HEADER);
    return (header != null && header.startsWith(BEARER_PREFIX))
        ? header.substring(BEARER_PREFIX.length())
        : header;
  }

  private String errorMessage(Response<?> response) {
    ResponseBody errorBody = response.errorBody();
    if (errorBody != null) {
      try {
        ErrorResponseDto error = moshi.adapter(ErrorResponseDto.class).fromJson(errorBody.string());
        if (error != null && error.getMessage() != null) {
          return error.getMessage();
        }
      } catch (IOException e) {
        // Fall through to the default message below.
      }
    }
    return DEFAULT_ERROR_MESSAGE;
  }

  public interface ResultCallback<T> {

    void onSuccess(T result);

    void onError(String message);

  }

}
