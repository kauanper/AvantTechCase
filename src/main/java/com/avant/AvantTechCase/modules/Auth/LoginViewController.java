package com.avant.AvantTechCase.modules.Auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class LoginViewController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Processa o formulário de login
    @PostMapping("/login")
    public String loginSubmit(String login, String password, HttpServletRequest request) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(login, password);
            var auth = this.authenticationManager.authenticate(usernamePassword);

            SecurityContextHolder.getContext().setAuthentication(auth);

            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return "redirect:/view/lists"; // Login sucesso: vai para as listas
        } catch (Exception e) {
            return "redirect:/view/login?error"; // Login falhou: volta com erro
        }
    }
}
