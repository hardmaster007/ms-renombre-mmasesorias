package cl.demo.renombre.config;

import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.demo.renombre.processor.UsuarioProcessor;


@Configuration
public class AppConfig {

    @Bean
    public JacksonDataFormat usuarioDataFormat() {
        return new JacksonDataFormat();
    }

    @Bean
    public UsuarioProcessor usuarioProcessor() {
        return new UsuarioProcessor();
    }
}