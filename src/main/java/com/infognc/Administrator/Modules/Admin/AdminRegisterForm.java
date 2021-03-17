package com.infognc.Administrator.Modules.Admin;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter @Configuration
public class AdminRegisterForm {

    private Long id;

    @Length(min = 2, max = 10)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{2,10}$")
    private String agentName;

    private String agentId;

    @NotBlank
    @Length(min = 8, max = 50)
    private String password;

    @Length(min = 8, max = 8)
    private String agentNum;

    private String part;

    private String level;

    private String agentCallNum;

    private List<String> roles;
}
