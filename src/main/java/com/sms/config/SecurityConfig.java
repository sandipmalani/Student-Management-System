package com.sms.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
    private CustomUserDetailsService userDetailsService;
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            	.requestMatchers("/admin-dashboard").permitAll()
                .requestMatchers("/teacher-dashboard","/student-dashboard").hasRole("TEACHER")
                .requestMatchers("/student-dashboard").hasRole("STUDENT")
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login").permitAll()  
                .loginProcessingUrl("doLogin").permitAll()
                //.successForwardUrl("/admin-dashboard")
                
//                .defaultSuccessUrl("/home")
//                .defaultSuccessUrl("/teacher-dashboard")
//                .defaultSuccessUrl("/student-dashboard")
                .successHandler(successHandler())
                 .permitAll()                
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll();
        
        return http.build();
        
       
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            String targetUrl = determineTargetUrl(authentication);
            response.sendRedirect(request.getContextPath() + targetUrl);
        };
    }
    
    
    protected String determineTargetUrl(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "/admin-dashboard";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"))) {
            return "/teacher-dashboard";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
            return "/student-dashboard";
        }
        return "/login";
    }
    
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return new SimpleUrlAuthenticationSuccessHandler() {
//           
//            protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//                for (GrantedAuthority authority : authorities) {
//                    if (authority.getAuthority().equals("ROLE_ADMIN")) {
//                        response.sendRedirect("/admin-dashboard");
//                        return;
//                    } else if (authority.getAuthority().equals("ROLE_TEACHER")) {
//                        response.sendRedirect("/teacher-dashboard");
//                        return;
//                    } else if (authority.getAuthority().equals("ROLE_STUDENT")) {
//                        response.sendRedirect("/student-dashboard");
//                        return;
//                    }
//                }
//                super.handle(request, response, authentication);
//            }
//        };
//    }
    
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return authenticationManagerBean();
//    }

    
/*
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals("ROLE_Admin")) {
                        response.sendRedirect("/admin-dashboard");
                        return ;
                    } else if (authority.getAuthority().equals("ROLE_Teacher")) {
                        response.sendRedirect("/teacher-dashboard");
                        return;
                    } else if (authority.getAuthority().equals("ROLE_Student")) {
                        response.sendRedirect("/student-dashboard");
                        return;
                    }
                }
                super.handle(request, response, authentication);
            }
        };
    }
*/
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.sendRedirect("/login?logout");
            }
        };
    }
	
	
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests(authorize -> authorize
//                .requestMatchers("/admin-dashboard").hasRole("Admin")
//                .requestMatchers("/teacher-dashboard").hasRole("Teacher")
//                .requestMatchers("/student-dashboard").hasRole("Student")
//                .anyRequest().permitAll()
//            )
//            .formLogin(form -> form
//                .loginPage("/login")
//                .successHandler(authenticationSuccessHandler()) // Use custom success handler
//                .permitAll()
//                
//            )
//            .logout(logout -> logout
//                .permitAll()
//            );
//            
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new SimpleUrlAuthenticationSuccessHandler() {
//            @Override
//            protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//                for (GrantedAuthority authority : authorities) {
//                    if (authority.getAuthority().equals("Admin")) {
//                        response.sendRedirect(request.getContextPath() + "/admin-dashboard"); // Use request.getContextPath() to get the application context root
//                        return;
//                    } else if (authority.getAuthority().equals("Teacher")) {
//                        response.sendRedirect(request.getContextPath() + "/teacher-dashboard");
//                        return;
//                    } else if (authority.getAuthority().equals("Student")) {
//                        response.sendRedirect(request.getContextPath() + "/student-dashboard");
//                        return;
//                    }
//                }
//                super.handle(request, response, authentication);
//            }
//        };
//    }
}

