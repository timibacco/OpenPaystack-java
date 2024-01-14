package openpaystack.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class CustomerQuery {
    private String email;
    private String first_name;
    private String last_name;
    private String phone;
    private Map<String,Object> metadata;



}
