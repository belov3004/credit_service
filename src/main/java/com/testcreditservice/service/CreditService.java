package com.testcreditservice.service;

import com.testcreditservice.model.CreditApplicationDto;
import com.testcreditservice.model.CreditRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreditService {
    CreditApplicationDto applyCreditApplication(CreditRequestDto request);
    List<CreditApplicationDto> getCreditApplications();
    List<CreditApplicationDto> getCreditApplicationsByUsername(String username);
    List<CreditApplicationDto> getConfirmedCreditApplications();
    List<CreditApplicationDto> getConfirmedCreditApplicationsByUsername(String username);
    void confirmCreditApplication(Long id);
}
