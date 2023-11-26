package com.barca.quoteengine.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.barca.quoteengine.controller.QuoteController;
import com.barca.quoteengine.dtos.QuoteCreationDto;
import com.barca.quoteengine.dtos.QuoteResponseDto;
import com.barca.quoteengine.services.QuoteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.*;

@WebMvcTest(controllers = QuoteController.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class QuoteControllerTests {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  QuoteService quoteService;

  @Test
  void getQuoteSample_should_return_200() throws Exception {

    // ARRANGE
    QuoteResponseDto dto = new QuoteResponseDto("1", "quote 1", "author 1");
    given(quoteService.getQuoteSample()).willReturn(List.of(dto));

    // ACT AND ASSERT
    mockMvc
        .perform(get("/quotes"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    // VERIFY
    verify(quoteService).getQuoteSample();
  }

  @Test
  void createQuote_should_return_201() throws Exception {

    // ARRANGE
    QuoteCreationDto dto = new QuoteCreationDto("quote 1", "author 1");

    // ACT AND ASSERT
    mockMvc
        .perform(post("/quotes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(dto)))
        .andExpect(status().isCreated());

    // VERIFY
    verify(quoteService).createQuote(dto);
  }

  @Test
  void createQuote_with_invalid_dto_should_return_400() throws Exception {

    // ARRANGE
    QuoteCreationDto dto = new QuoteCreationDto(null, null);

    // ACT AND ASSERT
    mockMvc
        .perform(post("/quotes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(dto)))
        .andExpect(status().isBadRequest());
  }

  private static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
