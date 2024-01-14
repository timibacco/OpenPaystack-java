package openpaystack.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;




public class Customer {

  private ApiConnect apiConnect;

    public JsonNode createCustomer(String email, String first_name, String last_name, String phone, Map<String,Object> metadata) throws Exception {
        // ...

        this.apiConnect = new ApiConnect(Constants.CUSTOMER_URL);

        return this.apiConnect.get(email,first_name,last_name,phone,metadata);


    }

}
