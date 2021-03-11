package com.infognc.Administrator.Modules.Counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CounselController {

    private final CounselService counselService;
    private final CounselRepository counselRepository;

    @GetMapping("/counsel/counselList")
    public String getCounselList(Model model) {

        List<Counsel> counsel = counselService.getCounsel();

        model.addAttribute("counsel", counsel);

        return "counsel/counselList";
    }

    @GetMapping("/data")
    public String getPageCounsel(Model model) {
        List<Object[]> counsel = counselRepository.data();

        model.addAttribute("counsel", counsel);

        return "counsel/counselList";
    }

    @GetMapping("/test")
    public String search(Model model) {


        return "test/test";
    }

}
