package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.EmployeeResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee/user")
public class EmployeeUserApi {
    private final EmployeeService employeeService;

    @GetMapping("find/all/{language}")
    public List<EmployeeResponse> findAll(@PathVariable Language language) {
        return employeeService.findAll(language);
    }

    @GetMapping("find/sort/name/asc/{language}")
    public List<EmployeeResponse> sortNameAsc(@PathVariable Language language) {
        return employeeService.findSortNameAsc(language);
    }

    @GetMapping("find/sort/name/desc/{language}")
    public List<EmployeeResponse> sortNameDesc(@PathVariable Language language) {
        return employeeService.findSortNameDesc(language);
    }

    @GetMapping("find/sort/data/asc/{language}")
    public List<EmployeeResponse> sortDataAsc(@PathVariable Language language) {
        return employeeService.findSortDataAsc(language);
    }

    @GetMapping("find/sort/data/desc/{language}")
    public List<EmployeeResponse> sortDataDesc(@PathVariable Language language) {
        return employeeService.findSortDataDesc(language);
    }

    @GetMapping("find/by/{employeeId}")
    public EmployeeResponse findById(@PathVariable Long employeeId) {
        return employeeService.findById(employeeId);
    }
}