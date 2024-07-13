package com.example.neftchi.repository;

import com.example.neftchi.dto.response.EmployeeResponse;
import com.example.neftchi.model.Employees;
import com.example.neftchi.model.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    @Query("""
            select new com.example.neftchi.dto.response.EmployeeResponse(
            e.id,
            e.image,
            e.fullName,
            e.jobTitle,
            e.description,
            e.dateCreated,
            e.language
            )
            from Employees e
            where e.language = :language
            order by e.id
            """)
    List<EmployeeResponse> findAllResponseSortById(@Param(value = "language") Language language);

    @Query("""
            select new com.example.neftchi.dto.response.EmployeeResponse(
            e.id,
            e.image,
            e.fullName,
            e.jobTitle,
            e.description,
            e.dateCreated,
            e.language
            )
            from Employees e
            where e.language = :language
            order by e.fullName
            """)
    List<EmployeeResponse> findSortNameAsc(@Param(value = "language") Language language);

    @Query("""
            select new com.example.neftchi.dto.response.EmployeeResponse(
            e.id,
            e.image,
            e.fullName,
            e.jobTitle,
            e.description,
            e.dateCreated,
            e.language
            )
            from Employees e
            where e.language = :language
            order by e.fullName desc
            """)
    List<EmployeeResponse> findSortNameDesc(@Param(value = "language") Language language);

    @Query("""
            select new com.example.neftchi.dto.response.EmployeeResponse(
            e.id,
            e.image,
            e.fullName,
            e.jobTitle,
            e.description,
            e.dateCreated,
            e.language
            )
            from Employees e
            where e.language = :language
            order by e.dateCreated
            """)
    List<EmployeeResponse> findSortDataAsc(@Param(value = "language") Language language);

    @Query("""
            select new com.example.neftchi.dto.response.EmployeeResponse(
            e.id,
            e.image,
            e.fullName,
            e.jobTitle,
            e.description,
            e.dateCreated,
            e.language
            )
            from Employees e
            where e.language = :language
            order by e.dateCreated desc
            """)
    List<EmployeeResponse> findSortDataDesc(@Param(value = "language") Language language);

    @Query("""
            select new com.example.neftchi.dto.response.EmployeeResponse(
            e.id,
            e.image,
            e.fullName,
            e.jobTitle,
            e.description,
            e.dateCreated,
            e.language
            )
            from Employees e
            where e.id = :employeeId
            """)
    EmployeeResponse findByIdResponse(@Param(value = "employeeId") Long employeeId);
}