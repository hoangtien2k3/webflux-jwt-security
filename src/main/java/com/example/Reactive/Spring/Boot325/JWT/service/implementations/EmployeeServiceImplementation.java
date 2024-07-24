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
package com.example.Reactive.Spring.Boot325.JWT.service.implementations;

import org.springframework.stereotype.Service;

import com.example.Reactive.Spring.Boot325.JWT.dto.EmployeeRequest;
import com.example.Reactive.Spring.Boot325.JWT.entity.Employee;
import com.example.Reactive.Spring.Boot325.JWT.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImplementation {

    private final EmployeeRepository employeeRepository;

    public Mono<Employee> createUser(EmployeeRequest employeeRequest) {
        Employee newEmployee = new Employee();
        newEmployee.setId(null);
        newEmployee.setUsername(employeeRequest.username());
        newEmployee.setPassword(employeeRequest.password());
        newEmployee.setRole(employeeRequest.role());
        newEmployee.setIsActive(true);

        return employeeRepository.save(newEmployee);
    }
}
