package com.infognc.Administrator.Modules.Counsel;


import com.infognc.Administrator.Modules.Role.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CounselService {

    private final CounselRepository counselRepository;

    @Transactional
    public List<Counsel> getCounsel() {
        return counselRepository.findAll();
    }

}
