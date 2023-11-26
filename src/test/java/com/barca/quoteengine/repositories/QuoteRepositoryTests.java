package com.barca.quoteengine.repositories;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.barca.quoteengine.configs.MongoConfig;
import com.barca.quoteengine.dtos.QuoteResponseDto;
import com.barca.quoteengine.model.Quote;

import jakarta.validation.ConstraintViolationException;

@DataMongoTest
@Transactional
@Import(MongoConfig.class)
@TestInstance(Lifecycle.PER_CLASS)

@ActiveProfiles("test")
class QuoteRepositoryTests {

  @Autowired
  private QuoteRepository quoteRepository;

  @BeforeAll // not a transaction
  void clearDatabase() {
    quoteRepository.deleteAll();
  }

  @ParameterizedTest
  @ValueSource(ints = { 0, 1, 2, 3 })
  void getQuoteSample_should_return_correct_sampleSize(int sampleSize) {

    // ARRANGE
    quoteRepository.save(new Quote(null, "Quote 1", "Author 1"));
    quoteRepository.save(new Quote(null, "Quote 2", "Author 2"));
    quoteRepository.save(new Quote(null, "Quote 3", "Author 3"));

    // ACT
    List<QuoteResponseDto> quote = quoteRepository.getQuoteSample(sampleSize);

    // ASSERT
    assertThat(quote).hasSize(sampleSize);

  }

  @Test
  void save_quote_should_fail_validation() {

    // ARRANGE
    Quote quote = new Quote(null, null, null);

    // ACT AND ASSERT
    assertThrowsExactly(ConstraintViolationException.class,
        () -> quoteRepository.save(quote));
  }
}
