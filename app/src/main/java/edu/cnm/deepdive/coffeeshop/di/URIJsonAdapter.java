package edu.cnm.deepdive.coffeeshop.di;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import java.net.URI;
import java.util.UUID;

final class URIJsonAdapter {

  @ToJson
  String toJson(URI uri) {
    return uri.toString();
  }

  @FromJson
  URI fromJson(String value) {
    return URI.create(value);
  }


}
