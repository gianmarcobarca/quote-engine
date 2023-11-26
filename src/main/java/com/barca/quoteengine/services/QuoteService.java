package com.barca.quoteengine.services;

import java.util.List;

import com.barca.quoteengine.dtos.QuoteCreationDto;
import com.barca.quoteengine.dtos.QuoteResponseDto;

public interface QuoteService {

  List<QuoteResponseDto> getQuoteSample();

  void createQuote(QuoteCreationDto quote);

}
