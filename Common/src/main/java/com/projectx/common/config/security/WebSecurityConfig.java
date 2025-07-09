package com.projectx.common.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final AuthTokenFilter authTokenFilter;
    //ToDo
    //Filters chain beani oluşturulacak .
        //Auth token filte ve user detail service bu filter chain içinde kullanılacak .
        //Cors config source beani filter chain içine method parametresi olarak verilecek .
    //Cost Config source beani oluşurulacak .

    // Modüller arası bağımlılık olmadan api çağrıları nasıl yapılır ?
}
