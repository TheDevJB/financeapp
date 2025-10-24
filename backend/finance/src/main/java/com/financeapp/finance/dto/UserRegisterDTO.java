package com.financeapp.finance.dto;

import lombok.Builder;

@Builder
public record UserRegisterDTO(
    String username,
    String email,
    String firstName,
    String lastName
)
{}
