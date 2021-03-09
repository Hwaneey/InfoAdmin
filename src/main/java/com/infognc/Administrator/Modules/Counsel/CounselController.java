package com.infognc.Administrator.Modules.Counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CounselController {

    private final CounselRepository counselRepository;

    @GetMapping("/counsel/counselList")
    public String getCounselList(Model model){

        List<Counsel> counsel = counselRepository.findAll();

        model.addAttribute("counsel",counsel);

        return "counsel/counselList";
    }

}
