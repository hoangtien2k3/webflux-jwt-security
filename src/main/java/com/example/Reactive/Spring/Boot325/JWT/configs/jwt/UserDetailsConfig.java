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
package com.example.Reactive.Spring.Boot325.JWT.configs.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Reactive.Spring.Boot325.JWT.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class UserDetailsConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Commented code for hard cording credentials
    /*@Bean
    MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    	var user = User.builder()
    			.username("adamk")
    			.password(passwordEncoder().encode("password"))
    			.roles("USER")
    			.build();
    	return new MapReactiveUserDetailsService(user);
    }*/
    @Bean
    ReactiveUserDetailsService userDetailsService(EmployeeRepository userRepository, PasswordEncoder passwordEncoder) {
        return (username) -> userRepository.findByUsername(username).map(user -> User.withUsername(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(user.getRole())
                .disabled(!user.getIsActive())
                .build());
    }
}
