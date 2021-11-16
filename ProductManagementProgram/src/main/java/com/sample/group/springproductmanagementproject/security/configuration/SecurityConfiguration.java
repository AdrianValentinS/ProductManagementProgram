package com.sample.group.springproductmanagementproject.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.sample.group.springproductmanagementproject.security.domain.UserPerms.READ;
import static com.sample.group.springproductmanagementproject.security.domain.UserPerms.WRITE;
import static com.sample.group.springproductmanagementproject.security.domain.UserRole.ADMIN;
import static com.sample.group.springproductmanagementproject.security.domain.UserRole.USER;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/main/search/display").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers(HttpMethod.GET,"/api/main/search/display").hasAuthority(READ.getPermission())
                .antMatchers(HttpMethod.GET,"/api/main/search/id/{productID}").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers(HttpMethod.GET,"/api/main/search/id/{productID}").hasAuthority(READ.getPermission())
                .antMatchers(HttpMethod.GET,"/api/main/search/name/{productName}").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers(HttpMethod.GET,"/api/main/search/name/{productName}").hasAuthority(READ.getPermission())
                .antMatchers(HttpMethod.POST,"/api/main/add").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.POST,"/api/main/add").hasAuthority(WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/api/main/delete/{productID}").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/api/main/delete/{productID}").hasAuthority(WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/api/main/update/{productID}").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/api/main/update/{productID}").hasAuthority(WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/api/security/login").permitAll()
                .defaultSuccessUrl("/api/main/search/display", true);

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user0 = org.springframework.security.core.userdetails.User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles(USER.name())
                .authorities(USER.getGrantedAuthorities())
                .build();

        UserDetails admin0 = org.springframework.security.core.userdetails.User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user0, admin0);
    }
}
