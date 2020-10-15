package com.okankaradag.bankapp;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/index")
    public String goHome() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        //Burada eğer giriş yapmışsak ve login sayfasına hala gitmek istiyorsak bizi ana sayfaya yönlendirme işlemi yapıyoruz.
        //Eğer kullanıcı null ise login sayfasına gidiyoruz .
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null ||  authentication instanceof AnonymousAuthenticationToken){
            return "Login";
        }
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "Login";
    }

    @GetMapping("/register")
    public String register() {
        return "Register";
    }

}
