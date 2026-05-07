package com.avant.AvantTechCase.modules.List;

import com.avant.AvantTechCase.modules.List.DTOs.ListRequestDTO;
import com.avant.AvantTechCase.modules.List.Services.CreateListUseCase;
import com.avant.AvantTechCase.modules.List.Services.FindByIdUseCase;
import com.avant.AvantTechCase.modules.List.Services.ListAllByUserUseCase;
import com.avant.AvantTechCase.modules.List.Services.UpdateByIdUseCase;
import com.avant.AvantTechCase.modules.Task.DTOs.TaskRequestDTO;
import com.avant.AvantTechCase.modules.Task.Services.CreateTaskUseCase;
import com.avant.AvantTechCase.modules.Task.Services.ListAllTasksByUserUseCase;
import com.avant.AvantTechCase.modules.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view")
public class HomeViewController {

    @Autowired private ListAllByUserUseCase listAllByUserUseCase;
    @Autowired private CreateListUseCase createListUseCase;
    @Autowired private FindByIdUseCase findListByIdUseCase;
    @Autowired private UpdateByIdUseCase updateListByIdUseCase;
    @Autowired private ListAllTasksByUserUseCase listAllTasksByUserUseCase;
    @Autowired private CreateTaskUseCase createTaskUseCase;

    @GetMapping("/home")
    public String home(Model model) {
        UserEntity user = getAuthenticatedUser();
        var lists = listAllByUserUseCase.execute(user.getId());
        model.addAttribute("lists", lists);
        model.addAttribute("userName", user.getLogin());
        return "home";
    }

    @PostMapping("/lists/create")
    public String createList(String title, String description) {
        UserEntity user = getAuthenticatedUser();
        ListRequestDTO dto = new ListRequestDTO(title, description);
        createListUseCase.execute(dto, user.getId());
        return "redirect:/view/home";
    }

    @GetMapping("/lists/{id}")
    public String listDetails(@PathVariable Long id, Model model) {
        UserEntity user = getAuthenticatedUser();

        var listDetails = findListByIdUseCase.execute(id, user.getId());
        var tasks = listAllTasksByUserUseCase.execute(id, user.getId());

        model.addAttribute("list", listDetails);
        model.addAttribute("tasks", tasks);
        model.addAttribute("userName", user.getLogin());
        return "tasks-view";
    }

    @PostMapping("/lists/{id}/update")
    public String updateList(@PathVariable Long id, String title, String description) {
        UserEntity user = getAuthenticatedUser();
        ListRequestDTO dto = new ListRequestDTO(title, description);
        updateListByIdUseCase.execute(id, user.getId(), dto);
        return "redirect:/view/lists/" + id;
    }

    @PostMapping("/tasks/create")
    public String createTask(TaskRequestDTO dto) {
        UserEntity user = getAuthenticatedUser();
        createTaskUseCase.execute(dto, user.getId());
        return "redirect:/view/lists/" + dto.listId();
    }

    private UserEntity getAuthenticatedUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}