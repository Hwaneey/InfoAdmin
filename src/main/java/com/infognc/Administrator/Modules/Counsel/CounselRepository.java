package com.infognc.Administrator.Modules.Counsel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CounselRepository extends JpaRepository<Counsel,Long> {


String sql2 = "select coun.agentnum,coun.custNum,agent.agentname,info.custName,cal.callNum,cal.startTime,cal.duration,coun.counStep,code.codename,cal.recNum \n" +
        " from counsel_list as coun  \n" +
        " left outer join call_list as cal \n" +
        " on coun.recNum = cal.recNum \n" +
        " left outer join customer_info as info \n" +
        " on coun.custNum = info.custNum \n" +
        " left outer join account_list as agent \n" +
        " on coun.agentnum = agent.agentnum \n" +
        " left outer join (select * from code_manager where codePart='00') as code \n" +
        " on coun.counStep = code.codeItem ";

    @Query(value=sql2, nativeQuery=true)
    public List<Object[]> data();

}
