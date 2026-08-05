package edu.cnm.deepdive.coffeeshop.di;

import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.coffeeshop.repository.SessionManager;
import edu.cnm.deepdive.coffeeshop.service.AuthApiService;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public final class NetworkModule {

  // TODO Point at the deployed server URL; this targets the emulator's host loopback.
  private static final String BASE_URL = "http://10.0.2.2:8080/";

  private NetworkModule() {
  }

  @Provides
  @Singleton
  static Moshi provideMoshi() {
    return new Moshi.Builder()
        .add(new UuidJsonAdapter())
        .build();
  }

  @Provides
  @Singleton
  static OkHttpClient provideOkHttpClient(SessionManager sessionManager) {
    Interceptor authInterceptor = (chain) -> {
      Request original = chain.request();
      String token = sessionManager.getToken();
      Request request = (token != null)
          ? original.newBuilder().header("Authorization", "Bearer " + token).build()
          : original;
      return chain.proceed(request);
    };
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
    return new OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build();
  }

  @Provides
  @Singleton
  static Retrofit provideRetrofit(OkHttpClient client, Moshi moshi) {
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build();
  }

  @Provides
  @Singleton
  static AuthApiService provideAuthApiService(Retrofit retrofit) {
    return retrofit.create(AuthApiService.class);
  }

}
