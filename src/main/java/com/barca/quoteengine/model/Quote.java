package com.barca.quoteengine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Document("quotes")
@Value
public class Quote {

  @Id
  String id;

  @Size(min = 3, max = 250)
  @NotNull
  String content;

  @Size(min = 1, max = 100)
  @NotNull
  String author;
}
