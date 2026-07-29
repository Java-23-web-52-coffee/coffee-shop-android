package edu.cnm.deepdive.coffeeshop.di;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import java.math.BigDecimal;

final class BigDecimalJsonAdapter {

  @ToJson
  String toJson(BigDecimal bigDecimal) {
    return bigDecimal.toString();
  }

  @FromJson
  BigDecimal fromJson(String value) {
    return new BigDecimal(value);
  }
}
