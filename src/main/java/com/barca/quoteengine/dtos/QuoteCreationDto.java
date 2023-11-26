package com.barca.quoteengine.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuoteCreationDto(
        @Size(min = 3, max = 250) @NotNull String content, @Size(min = 1, max = 100) @NotNull String author) {

}
