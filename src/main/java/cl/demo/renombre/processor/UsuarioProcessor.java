package cl.demo.renombre.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class UsuarioProcessor implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonResponse = exchange.getIn().getBody(String.class);
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        
        if (rootNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) rootNode;
            for (JsonNode node : arrayNode) {
                // Transformar nombres a mayúsculas
                if (node.has("nombre")) {
                    String nombre = node.get("nombre").asText();
                    ((com.fasterxml.jackson.databind.node.ObjectNode) node).put("nombre", nombre.toUpperCase());
                }
            }
            exchange.getIn().setBody(objectMapper.writeValueAsString(arrayNode));
        }
    }
}
