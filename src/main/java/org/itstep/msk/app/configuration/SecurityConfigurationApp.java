package org.itstep.msk.app.configuration;

import org.itstep.msk.app.service.CustomerDetails;
import org.itstep.msk.app.service.FailedAttemptLoginHandler;
import org.itstep.msk.app.service.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("org.itstep.msk.app.service")
public class SecurityConfigurationApp extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomerDetails();
    }

    @Autowired
    private JwtRequestFilter filter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private DataSource dataSource;

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**")
                .antMatchers("/templates/**")
                .antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()

                .antMatchers("/", "/**").permitAll()
                .antMatchers("/register/**","/login-locked","/login-disable").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/customers*/**").fullyAuthenticated()
                .antMatchers("/products/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/products/**").hasAnyAuthority("ADMIN")
                .antMatchers("/admin**").hasAnyAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").failureHandler(new FailedAttemptLoginHandler())
                .defaultSuccessUrl("/registersuccess")
                .failureUrl("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and().rememberMe().key("AbcDefHijKnRopQs_12345")
                .tokenValiditySeconds(7 * 24 * 60 * 60)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


    }
}
