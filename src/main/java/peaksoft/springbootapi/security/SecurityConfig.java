package peaksoft.springbootapi.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import peaksoft.springbootapi.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable().
                authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/api/companies1").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/companies1/{id}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,".api/companies1/{id}").hasAnyAuthority("ADMIN","INSTRUCTOR")
                .antMatchers(HttpMethod.GET,"/api/users").authenticated()
                .antMatchers(HttpMethod.GET,"/aoi/users/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/users").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
}
}
