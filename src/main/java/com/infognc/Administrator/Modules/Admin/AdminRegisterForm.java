package com.infognc.Administrator.Modules.Admin;

import lombok.*;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter @Configuration
public class AdminRegisterForm {

    private Long id;

    private String agentName;

    private String agentId;

    private String password;

    private String agentNum;

    private String part;

    private String level;

    private String agentCallNum;


}
