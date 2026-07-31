package edu.cnm.deepdive.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public record InterestCreateDto(@JsonProperty(access = Access.WRITE_ONLY) String category) {

}
