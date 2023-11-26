package com.barca.quoteengine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.barca.quoteengine.dtos.QuoteCreationDto;
import com.barca.quoteengine.dtos.QuoteResponseDto;
import com.barca.quoteengine.services.QuoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuoteController {

  private final QuoteService quoteService;

  @GetMapping
  public List<QuoteResponseDto> getQuoteSample() {
    return quoteService.getQuoteSample();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createQuote(@RequestBody @Valid QuoteCreationDto dto) {
    quoteService.createQuote(dto);
  }

}
