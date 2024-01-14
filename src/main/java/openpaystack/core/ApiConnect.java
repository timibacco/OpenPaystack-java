package openpaystack.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
public class ApiConnect {


    @Value("${paystack.secret.key}")
    private String API_SECRET_KEY;

    @Autowired
    private final String url;

    private void checkAPISecretKey() {
        try {
            Assert.hasText(API_SECRET_KEY, "API_SECRET_KEY is not set");
        } catch (IllegalArgumentException e) {
            Logger.getLogger(ApiConnect.class.getName()).log(Level.SEVERE, "Cannot find property ${paystack.secret.key} in application.properties", e );


        }
    }



    public JsonNode get(String email, String first_name, String last_name, String phone, Map<String,Object> metadata) throws Exception {

        try {

            checkAPISecretKey();
            CustomerQuery query = new CustomerQuery(email, first_name, last_name, phone, metadata);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_SECRET_KEY);
            HttpEntity<CustomerQuery> request = new HttpEntity<>(query, headers);

            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    JsonNode.class
            );
            return response.getBody();

        } catch (Exception e) {
            Logger.getLogger(ApiConnect.class.getName()).log(Level.SEVERE,null , e );

            throw new Exception(e.getMessage());
        }
    }
}
