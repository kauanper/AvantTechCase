package com.avant.AvantTechCase.modules.List;

import org.springframework.ui.Model;
import com.avant.AvantTechCase.modules.List.DTOs.ListRequestDTO;
import com.avant.AvantTechCase.modules.List.Services.CreateListUseCase;
import com.avant.AvantTechCase.modules.List.Services.ListAllByUserUseCase;
import com.avant.AvantTechCase.modules.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class HomeViewController {

    @Autowired
    private ListAllByUserUseCase listAllByUserUseCase;

    @Autowired
    private CreateListUseCase createListUseCase;

    @GetMapping("/home")
    public String home(Model model) { // Agora o Model será reconhecido
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var lists = listAllByUserUseCase.execute(user.getId());

        model.addAttribute("lists", lists);
        model.addAttribute("userName", user.getLogin());
        return "home";
    }

    @PostMapping("/lists/create")
    public String createList(String title, String description) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ListRequestDTO dto = new ListRequestDTO(title, description);
        createListUseCase.execute(dto, user.getId());

        return "redirect:/view/home";
    }
}