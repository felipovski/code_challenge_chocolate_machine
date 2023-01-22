package com.chococompany.control.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record ChocolateTypeDtoV1(
        @Pattern(regexp = "[a-cA-C]")
        @Size(min = 1, max = 1)
        @NotBlank
        String type
){}
