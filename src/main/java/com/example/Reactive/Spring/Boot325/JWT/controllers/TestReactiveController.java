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
package com.example.Reactive.Spring.Boot325.JWT.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationRequest;
import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationResponse;
import com.example.Reactive.Spring.Boot325.JWT.dto.EmployeeRequest;
import com.example.Reactive.Spring.Boot325.JWT.service.implementations.AuthenticationServiceImplementation;
import com.example.Reactive.Spring.Boot325.JWT.service.implementations.EmployeeServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/test")
@Slf4j
@AllArgsConstructor
public class TestReactiveController {

    AuthenticationServiceImplementation authenticationServiceImplementation;
    EmployeeServiceImplementation employeeServiceImplementation;

    @GetMapping(path = "/unsecured")
    public Mono<String> helloWorldUnSecured() {
        return Mono.just("Hello World Un-Secured");
    }

    @GetMapping(path = "/secured")
    public Mono<String> helloWorldSecured() {
        return Mono.just("Hello World Secured");
    }

    @PostMapping(path = "/login")
    public Mono<AuthenticationResponse> customLogin(@RequestBody final AuthenticationRequest authenticationRequest) {
        return authenticationServiceImplementation.authenticateRequest(authenticationRequest);
    }

    @PostMapping(path = "/createUser")
    public Mono<?> createUser(@RequestBody final EmployeeRequest employeeRequest) {
        return employeeServiceImplementation.createUser(employeeRequest);
    }
}
