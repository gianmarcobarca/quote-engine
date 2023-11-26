package com.barca.quoteengine.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.barca.quoteengine.dtos.QuoteResponseDto;
import com.barca.quoteengine.model.Quote;

public interface QuoteRepository extends MongoRepository<Quote, String> {

  @Aggregation("{$sample: {size: ?0}}")
  List<QuoteResponseDto> getQuoteSample(int num);
}
