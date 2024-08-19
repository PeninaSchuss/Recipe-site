package hac;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig  {
    /**
     * This method is used to create users with different roles.
     * @param bCryptPasswordEncoder PasswordEncoder
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("ADMIN")
                .build());
        manager.createUser(User.withUsername("useradmin")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

    /**
     * This method is used to encrypt the password.
     * @return PasswordEncoder
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * This method is used to configure the security.
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/**", "/.sass-cache/**", "/fonts/**", "/img/**", "/js/**","/scss/**","/style.css","/fragments/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/**").permitAll()
                )
                .formLogin((form) -> form
                        .loginProcessingUrl("/login")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling
                                .accessDeniedPage("/403")
                );
        return http.build();
    }

    /**
     * This method is used to ignore the favicon.ico request.
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/favicon.ico");
    }

}
