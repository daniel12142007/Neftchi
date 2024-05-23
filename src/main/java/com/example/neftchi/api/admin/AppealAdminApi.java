package com.example.neftchi.api.admin;

import com.example.neftchi.dto.response.AppealResponseAll;
import com.example.neftchi.dto.response.AppealResponseOne;
import com.example.neftchi.service.AppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/appeal/admin")
public class AppealAdminApi {
    private final AppealService appealService;

    @GetMapping("find/all/11")
    public List<AppealResponseAll> findAll11() {
        return appealService.findAll11();
    }

    @GetMapping("find/all")
    public List<AppealResponseAll> findAll() {
        return appealService.findAll();
    }

    @GetMapping("find/sort/name/asc")
    public List<AppealResponseAll> findNameAsc() {
        return appealService.findSortNameAsc();
    }

    @GetMapping("find/sort/name/desct")
    public List<AppealResponseAll> findNameDesc() {
        return appealService.findSortNameDesc();
    }

    @GetMapping("find/sort/email/asc")
    public List<AppealResponseAll> findEmailAsc() {
        return appealService.findSortEmailAsc();
    }

    @GetMapping("find/sort/email/desc")
    public List<AppealResponseAll> findEmailDesc() {
        return appealService.findSortEmailDesc();
    }

    @GetMapping("find/sort/number/asc")
    public List<AppealResponseAll> findNumbersAsc() {
        return appealService.findSortNumberAsc();
    }

    @GetMapping("find/sort/number/desc")
    public List<AppealResponseAll> findNumberDesc() {
        return appealService.findSortNumberDesc();
    }

    @GetMapping("find/sort/status/asc")
    public List<AppealResponseAll> findStatusAsc() {
        return appealService.findSortStatusAsc();
    }

    @GetMapping("find/sort/status/desc")
    public List<AppealResponseAll> findStatusDesc() {
        return appealService.findSortStatusDesc();
    }

    @GetMapping("find/by/{id}")
    public AppealResponseOne findById(@PathVariable Long id) {
        return appealService.findById(id);
    }

    @PostMapping("save/answer/{id}")
    public AppealResponseOne answer(@PathVariable Long id,
                                    @RequestParam String answer) {
        return appealService.saveAnswer(id, answer);
    }

    @DeleteMapping("delete/by/{id}")
    public List<AppealResponseAll> deleteById(@PathVariable Long id) {
        return appealService.deleteById(id);
    }
}