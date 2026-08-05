package edu.cnm.deepdive.coffeeshop.repository;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.coffeeshop.model.dto.ProfileDto;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Persists the signed-in profile's bearer token and identity across app restarts. Plain
 * {@link SharedPreferences}, not encrypted — no crypto dependency is declared in this project.
 */
@Singleton
public class SessionManager {

  private static final String PREFS_NAME = "session";
  private static final String KEY_TOKEN = "token";
  private static final String KEY_PROFILE_ID = "profile_id";
  private static final String KEY_PROFILE_NAME = "profile_name";

  private final SharedPreferences preferences;

  @Inject
  public SessionManager(@ApplicationContext Context context) {
    preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
  }

  public void saveSession(String token, ProfileDto profile) {
    preferences.edit()
        .putString(KEY_TOKEN, token)
        .putString(KEY_PROFILE_ID, profile.getId().toString())
        .putString(KEY_PROFILE_NAME, profile.getName())
        .apply();
  }

  public void clearSession() {
    preferences.edit().clear().apply();
  }

  public String getToken() {
    return preferences.getString(KEY_TOKEN, null);
  }

  public UUID getProfileId() {
    String id = preferences.getString(KEY_PROFILE_ID, null);
    return (id != null) ? UUID.fromString(id) : null;
  }

  public String getProfileName() {
    return preferences.getString(KEY_PROFILE_NAME, null);
  }

  public boolean isSignedIn() {
    return getToken() != null;
  }

}
