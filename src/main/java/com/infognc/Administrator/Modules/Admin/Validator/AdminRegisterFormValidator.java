package com.infognc.Administrator.Modules.Admin.Validator;


import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.Admin.AdminRegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component @RequiredArgsConstructor
public class AdminRegisterFormValidator implements Validator {

   private final AccountRepository accountRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(AdminRegisterForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AdminRegisterForm adminRegisterForm = (AdminRegisterForm) target;

        if (accountRepository.existsByAgentId(adminRegisterForm.getAgentId())){
            errors.rejectValue("agentId", "invalid.agentId", new Object[]{adminRegisterForm.getAgentId()}, "이미 사용중인 아이디입니다.");
        }
        if (accountRepository.existsByAgentNum(adminRegisterForm.getAgentNum())) {
            errors.rejectValue("agentNum", "invalid.agentNum", new Object[]{adminRegisterForm.getAgentNum()}, "이미 사용중인 사원번호입니다.");
        }
    }
}
