package cl.demo.renombre.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import cl.demo.renombre.processor.UsuarioProcessor;


@Component
public class UsuarioRoute extends RouteBuilder {

    private final UsuarioProcessor usuarioProcessor;

    public UsuarioRoute(UsuarioProcessor usuarioProcessor) {
        this.usuarioProcessor = usuarioProcessor;
    }

    @Override
    public void configure() throws Exception {
        // Ruta principal que se ejecuta cada 10 segundos
        from("timer:usuarioTimer?period=10000")
            .log("Iniciando consumo del servicio de usuarios...")
            .to("http://localhost:8090/usuarios?bridgeEndpoint=true")
            .process(usuarioProcessor)
            .log("Datos transformados: ${body}")
            .to("log:output?showAll=true");
    }
}