package com.testcreditservice.service;

import com.testcreditservice.api.exceptions.CreditLimitException;
import com.testcreditservice.api.exceptions.NotFoundException;
import com.testcreditservice.dao.CreditApplicationDao;
import com.testcreditservice.dao.UserDao;
import com.testcreditservice.entity.CreditApplicationEntity;
import com.testcreditservice.entity.UserEntity;
import com.testcreditservice.model.CreditApplicationDto;
import com.testcreditservice.model.CreditRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    CreditApplicationDao creditApplicationDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ConfigurationService configurationService;

    @Override
    @Transactional
    public CreditApplicationDto applyCreditApplication(CreditRequestDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserEntity user = null;

        try {
            user = (UserEntity) principal;
        } catch (ClassCastException e) {
            throw new InternalAuthenticationServiceException("Only authorized users can apply credit applications");
        }

        String limitStr = configurationService.getConfig(ConfigurationServiceImpl.CREDIT_APPLICATIONS_LIMIT);
        if (limitStr != null) {
            try {

                Long applicationsCount = creditApplicationDao.countAllByTimestampAfterAndCountry(Date.from(Instant.now().minus(1, ChronoUnit.MINUTES)), request.getCountry());
                System.out.println(applicationsCount);
                if (applicationsCount >= Long.parseLong(limitStr))
                    throw new CreditLimitException(400, "Submission limit is reached. Please try again later");
            } catch (NumberFormatException e) {
            }
        }

        CreditApplicationEntity creditApplicationEntity = convertToEntity(request, user);
        CreditApplicationEntity result = creditApplicationDao.save(creditApplicationEntity);

        return convertToDto(result);
    }

    @Override
    @Transactional
    public List<CreditApplicationDto> getCreditApplications() {
        List<CreditApplicationDto> creditApplicationDtos = new LinkedList<>();
        for (CreditApplicationEntity entity : creditApplicationDao.findAll()) {
            creditApplicationDtos.add(convertToDto(entity));
        }
        return creditApplicationDtos;
    }

    @Override
    @Transactional
    public List<CreditApplicationDto> getCreditApplicationsByUsername(String username) {
        UserEntity user = userDao.findByUserName(username);
        if (user == null)
            throw new NotFoundException(404, "Username not found");

        List<CreditApplicationDto> creditApplicationDtos = new LinkedList<>();
        for (CreditApplicationEntity entity : creditApplicationDao.findAllByUser(user)) {
            creditApplicationDtos.add(convertToDto(entity));
        }
        return creditApplicationDtos;
    }

    @Override
    public List<CreditApplicationDto> getConfirmedCreditApplications() {
        List<CreditApplicationDto> creditApplicationDtos = new LinkedList<>();
        for (CreditApplicationEntity entity : creditApplicationDao.findAllByConfirmed(true)) {
            creditApplicationDtos.add(convertToDto(entity));
        }
        return creditApplicationDtos;
    }

    @Override
    public List<CreditApplicationDto> getConfirmedCreditApplicationsByUsername(String username) {
        UserEntity user = userDao.findByUserName(username);
        if (user == null)
            throw new NotFoundException(404, "Username not found");

        List<CreditApplicationDto> creditApplicationDtos = new LinkedList<>();
        for (CreditApplicationEntity entity : creditApplicationDao.findAllByUserAndConfirmed(user, true)) {
            creditApplicationDtos.add(convertToDto(entity));
        }
        return creditApplicationDtos;
    }

    @Override
    @Transactional
    public void confirmCreditApplication(Long id) {
        Optional<CreditApplicationEntity> result = creditApplicationDao.findById(id);
        if (!result.isPresent())
            throw new NotFoundException(404, "Credit application not found");
        CreditApplicationEntity entity = result.get();
        entity.setConfirmed(true);
        creditApplicationDao.save(entity);
    }

    private CreditApplicationEntity convertToEntity(CreditRequestDto creditRequestDto, UserEntity user) {
        CreditApplicationEntity creditApplicationEntity = new CreditApplicationEntity();
        creditApplicationEntity.setUser(user);
        creditApplicationEntity.setAmount(creditRequestDto.getAmount());
        creditApplicationEntity.setCountry(creditRequestDto.getCountry());
        creditApplicationEntity.setTerm(creditRequestDto.getTerm());
        creditApplicationEntity.setConfirmed(false);
        return creditApplicationEntity;
    }

    private CreditApplicationDto convertToDto(CreditApplicationEntity creditApplicationEntity) {
        CreditApplicationDto creditApplicationDto = new CreditApplicationDto();
        creditApplicationDto.setFirstName(creditApplicationEntity.getUser().getFirstName());
        creditApplicationDto.setLastName(creditApplicationEntity.getUser().getLastName());
        creditApplicationDto.setPersonalCode(creditApplicationEntity.getUser().getPersonalCode());
        creditApplicationDto.setId(creditApplicationEntity.getId());
        creditApplicationDto.setAmount(creditApplicationEntity.getAmount());
        creditApplicationDto.setCountry(creditApplicationEntity.getCountry());
        creditApplicationDto.setTerm(creditApplicationEntity.getTerm());
        creditApplicationDto.setConfirmed(creditApplicationEntity.getConfirmed());
        creditApplicationDto.setTimestamp(creditApplicationEntity.getTimestamp().toInstant().toString());
        return creditApplicationDto;
    }
}
