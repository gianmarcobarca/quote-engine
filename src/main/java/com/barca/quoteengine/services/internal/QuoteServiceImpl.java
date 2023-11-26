package com.barca.quoteengine.services.internal;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barca.quoteengine.dtos.QuoteCreationDto;
import com.barca.quoteengine.dtos.QuoteResponseDto;
import com.barca.quoteengine.model.Quote;
import com.barca.quoteengine.repositories.QuoteRepository;
import com.barca.quoteengine.services.QuoteService;

import lombok.RequiredArgsConstructor;

@Service("quoteService")
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

  private final QuoteRepository quoteRepository;

  @Override
  public List<QuoteResponseDto> getQuoteSample() {
    return quoteRepository.getQuoteSample(1);
  }

  @Override
  public void createQuote(QuoteCreationDto quote) {
    quoteRepository.save(new Quote(null, quote.content(), quote.author()));
  }

}
