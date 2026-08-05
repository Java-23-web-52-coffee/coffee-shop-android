package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.ProfileDto;
import edu.cnm.deepdive.coffeeshop.model.dto.SignInRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.SignUpRequestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApiService {

  @POST("apis/sign-up")
  Call<ProfileDto> signUp(@Body SignUpRequestDto request);

  @POST("apis/sign-in")
  Call<ProfileDto> signIn(@Body SignInRequestDto request);

  @POST("apis/sign-out")
  Call<Void> signOut();

}
