package com.testcreditservice.api;

import com.testcreditservice.model.CreditApplicationDto;
import com.testcreditservice.model.CreditRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/")
public interface CreditApi {

    @PostMapping(value = "/credits")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    ResponseEntity<CreditApplicationDto> applyCreditApplication(@RequestBody CreditRequestDto body);

    @GetMapping(value = "/credits")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    ResponseEntity<List<CreditApplicationDto>> getCreditApplications(@RequestParam(value = "confirmed", required = false) Boolean confirmed);

    @GetMapping(value = "/credits/{username}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    ResponseEntity<List<CreditApplicationDto>> getCreditApplication(@PathVariable("username") String username, @RequestParam(value = "confirmed", required = false) Boolean confirmed);

    @GetMapping(value = "/credits/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    ResponseEntity<Void> confirmCreditApplication(@PathVariable("id") Long id);

}
