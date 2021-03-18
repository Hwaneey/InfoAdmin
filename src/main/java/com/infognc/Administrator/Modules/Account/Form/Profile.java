package com.infognc.Administrator.Modules.Account.Form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Profile {

    private String agentNum;

    private String agentId;

    private String agentName;

    private String password;

    private String part;

    private String level;

    private String agentCallNum;

    private String status ;

    private LocalDateTime regDate;

    private LocalDateTime lastDate;

    private LocalDateTime pwDate;

}
