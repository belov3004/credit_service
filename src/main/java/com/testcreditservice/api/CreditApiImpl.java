package com.testcreditservice.api;

import com.testcreditservice.model.CreditApplicationDto;
import com.testcreditservice.model.CreditRequestDto;
import com.testcreditservice.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditApiImpl implements CreditApi {

    @Autowired
    private CreditService creditService;

    @Override
    public ResponseEntity<CreditApplicationDto> applyCreditApplication(CreditRequestDto body) {
        CreditApplicationDto creditApplicationDto = creditService.applyCreditApplication(body);
        return new ResponseEntity<>(creditApplicationDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CreditApplicationDto>> getCreditApplications(Boolean confirmed) {
        List<CreditApplicationDto> creditApplicationDto;
        if (confirmed != null && confirmed)
            creditApplicationDto = creditService.getConfirmedCreditApplications();
        else
            creditApplicationDto = creditService.getCreditApplications();
        return new ResponseEntity<>(creditApplicationDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CreditApplicationDto>> getCreditApplication(String username, Boolean confirmed) {
        List<CreditApplicationDto> creditApplicationDto;
        if (confirmed != null && confirmed)
            creditApplicationDto = creditService.getConfirmedCreditApplicationsByUsername(username);
        else
            creditApplicationDto = creditService.getCreditApplicationsByUsername(username);
        return new ResponseEntity<>(creditApplicationDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> confirmCreditApplication(Long id) {
        creditService.confirmCreditApplication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
