package com.example.neftchi.api.admin;

import com.example.neftchi.dto.request.EmployeeRequest;
import com.example.neftchi.dto.response.EmployeeResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/employee/admin")
public class EmployeeAdminApi {
    private final EmployeeService employeeService;

    @PostMapping("save/{language}")
    public List<EmployeeResponse> save(@PathVariable Language language,
                                       @RequestBody EmployeeRequest request) {
        return employeeService.save(request, language);
    }

    @PutMapping("update/{employeeId}")
    public List<EmployeeResponse> update(@PathVariable Long employeeId,
                                         @RequestBody EmployeeRequest request) {
        return employeeService.updateById(employeeId, request);
    }

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

    @DeleteMapping("delete/by/{employeeId}/{language}")
    public List<EmployeeResponse> deleteById(@PathVariable Long employeeId,
                                             @PathVariable Language language) {
        return employeeService.deleteById(employeeId, language);
    }
}