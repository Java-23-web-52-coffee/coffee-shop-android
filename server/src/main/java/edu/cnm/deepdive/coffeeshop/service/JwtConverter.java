/*
 *  Copyright 2026 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final ProfileRepository profileRepository;

  public JwtConverter(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
    String name = jwt.getClaimAsString("name");
    if (name == null || name.isBlank()) {
      throw invalidToken("JWT does not contain a usable name claim");
    }
    var profile = profileRepository.findByName(name)
        .orElseThrow(() -> invalidToken("JWT name does not identify an existing profile"));
    var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
    return new UsernamePasswordAuthenticationToken(profile, jwt.getTokenValue(), authorities);
  }

  private static OAuth2AuthenticationException invalidToken(String description) {
    return new OAuth2AuthenticationException(
        new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN, description, null));
  }
}
