package com.infognc.Administrator.Infra.Listener;

import com.infognc.Administrator.Infra.Config.PasswordConfig;

import com.infognc.Administrator.Modules.Account.Account;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.Role.Role;
import com.infognc.Administrator.Modules.Role.RoleRepository;
import com.infognc.Administrator.Modules.RoleHierarchy.RoleHierarchy;
import com.infognc.Administrator.Modules.RoleHierarchy.RoleHierarchyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final RoleHierarchyRepository roleHierarchyRepository;
    private final PasswordConfig passwordConfig;
    private final String randomHangulName() {
        List<String> 성 = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> 이름 = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(성);
        Collections.shuffle(이름);
        return 성.get(0) + 이름.get(0) + 이름.get(1);
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        setupSecurityResources();
        alreadySetup = true;
    }


    private void setupSecurityResources() {
        Set<Role> roles = new HashSet<>();
        Role adminRole = createRoleIfNotFound("0", "관리자");
        Role middleAdminRole = createRoleIfNotFound("1", "중간관리자");
        Role qcRole = createRoleIfNotFound("2", "QC");
        Role consultantRole = createRoleIfNotFound("5", "상담원");

        createRoleHierarchyIfNotFound(middleAdminRole, adminRole);
        createRoleHierarchyIfNotFound(qcRole, middleAdminRole);
        createRoleHierarchyIfNotFound(consultantRole, qcRole);

        roles.add(adminRole);
        createUserIfNotFound("20211000", "admin", "관리자", "123", "01034263345", roles);
        createUserIfNotFound("20211001", "a", "에이전트", "1", "01034263345", roles);
        roles.clear();
        roles.add(consultantRole);
        for (int i = 1002; i <= 1500; i++) {
                createUserIfNotFound("2021" + i, "a" + i, randomHangulName(), "1", "010" + "3123" + i, roles);
        }
    }


    public Account createUserIfNotFound(final String agentNum, final String agentId,
                                        final String agentName, final String password, String agentCallNum, Set<Role> roleSet) {
        Account account = accountRepository.findByAgentId(agentId);

        if (account == null) {
            account = Account.builder()
                    .agentNum(agentNum)
                    .agentId(agentId)
                    .agentName(agentName)
                    .password(passwordConfig.passwordEncoder().encode(password))
                    .part("00")
                    .level("0")
                    .agentCallNum(agentCallNum)
                    .status("1")
                    .regDate(LocalDateTime.now())
                    .delFlag("N")
                    .userRoles(roleSet)
                    .build();
        }
        return accountRepository.save(account);
    }


    @Transactional
    public Role createRoleIfNotFound(String roleName, String roleDesc) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(role);
    }


    @Transactional
    public void createRoleHierarchyIfNotFound(Role childRole, Role parentRole) {
        RoleHierarchy roleHierarchy = roleHierarchyRepository.findByChildName(parentRole.getRoleName());
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy.builder()
                    .childName(parentRole.getRoleName())
                    .build();
        }
        RoleHierarchy parentRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);

        roleHierarchy = roleHierarchyRepository.findByChildName(childRole.getRoleName());
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy.builder()
                    .childName(childRole.getRoleName())
                    .build();
        }
        RoleHierarchy childRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);

        childRoleHierarchy.setParentName(parentRoleHierarchy);
    }


}
