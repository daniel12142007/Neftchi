package com.example.neftchi.service;

import com.example.neftchi.dto.request.EmployeeRequest;
import com.example.neftchi.dto.response.EmployeeResponse;
import com.example.neftchi.model.Employees;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeesRepository employeesRepository;

    public List<EmployeeResponse> save(EmployeeRequest request,
                                       Language language) {
        Employees employees = Employees.builder()
                .fullName(request.fullName())
                .image(request.image())
                .jobTitle(request.jobTitle())
                .description(request.description())
                .language(language)
                .dateCreated(LocalDateTime.now())
                .build();
        employeesRepository.save(employees);
        return findAll(language);
    }

    public List<EmployeeResponse> updateById(Long employeeId,
                                             EmployeeRequest request) {
        Employees employees = employeesRepository.findById(employeeId).orElseThrow();
        employees.setFullName(request.fullName());
        employees.setImage(request.image());
        employees.setJobTitle(request.jobTitle());
        employees.setDescription(request.description());
        employeesRepository.save(employees);
        return findAll(employees.getLanguage());
    }

    public List<EmployeeResponse> deleteById(Long employeeId,
                                             Language language) {
        employeesRepository.deleteById(employeeId);
        return findAll(language);
    }

    public List<EmployeeResponse> findAll(Language language) {
        return employeesRepository.findAllResponseSortById(language);
    }

    public List<EmployeeResponse> findSortNameAsc(Language language) {
        return employeesRepository.findSortNameAsc(language);
    }

    public List<EmployeeResponse> findSortNameDesc(Language language) {
        return employeesRepository.findSortNameDesc(language);
    }

    public List<EmployeeResponse> findSortDataAsc(Language language) {
        return employeesRepository.findSortDataAsc(language);
    }

    public List<EmployeeResponse> findSortDataDesc(Language language) {
        return employeesRepository.findSortDataDesc(language);
    }

    public EmployeeResponse findById(Long employeeId) {
        return employeesRepository.findByIdResponse(employeeId);
    }
}