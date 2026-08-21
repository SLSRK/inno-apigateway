package com.innowise.apigateway.client;

import com.innowise.apigateway.model.dto.ValidateRequestDto;
import com.innowise.apigateway.model.dto.ValidateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthClient {

    private final WebClient webClient;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public Mono<ValidateResponseDto> validate(String token) {
        return webClient.post()
                .uri(authServiceUrl + "/api/v1/auth/validate")
                .bodyValue(new ValidateRequestDto(token))
                .retrieve()
                .bodyToMono(ValidateResponseDto.class);
    }
}
