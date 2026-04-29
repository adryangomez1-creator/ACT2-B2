package com.adryangomez.tienda.config;

import com.adryangomez.tienda.entity.Usuario;
import com.adryangomez.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Configuration
public class SecurityConfig {

    /*
    Crearemos un objeto para encriptar la contraseña
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Crearemos un objeto para el usuario y la constraseña de la aplicacion
    @Service
    public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Override
        public UserDetails loadUserByUsername(String username) {
            Usuario usuario = usuarioRepository.findByUsername(username);

            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }

            return User.builder()
                    .username(usuario.getUsername())
                    .password(usuario.getPassword())
                    .roles(usuario.getRol().name().replace("ROLE_", ""))
                    .build();
        }
    }

    // Crearemos un objeto para la configuracion de las rutas de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/guardar-usuario",
                                "/css/**", "/js/**", "/images/**").permitAll()

                        // SOLO ADMIN puede acceder a usuarios
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")

                        // ADMIN y USER pueden acceder al resto
                        .requestMatchers("/categoria/**", "/detallepedido/**", "/pedido/**", "/producto/**")
                        .hasAnyRole("ADMIN", "USER")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(e -> e .accessDeniedPage("/denegado"));



        return http.build();
    }
}