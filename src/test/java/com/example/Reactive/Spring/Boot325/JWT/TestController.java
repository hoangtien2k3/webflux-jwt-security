/*
 * Copyright 2024-2024 - Hoàng Anh Tiến
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.Reactive.Spring.Boot325.JWT;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationRequest;
import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationResponse;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {
    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("Correct Credentials")
    void shouldLogin() {

        // given
        AuthenticationRequest loginRequest = new AuthenticationRequest("adamk", "password");

        // when
        AuthenticationResponse response = webTestClient
                .post()
                .uri("/login")
                .body(BodyInserters.fromValue(loginRequest))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AuthenticationResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(response).isNotNull();
        assertThat(response.token()).isNotBlank();
    }

    @Test
    @DisplayName("Incorrect Credentials")
    void shouldNotLoginWithWrongPassword() {

        // given
        AuthenticationRequest loginRequest = new AuthenticationRequest("adamk", "passwedeord");

        // when / then
        webTestClient
                .post()
                .uri("/login")
                .body(BodyInserters.fromValue(loginRequest))
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }
}
