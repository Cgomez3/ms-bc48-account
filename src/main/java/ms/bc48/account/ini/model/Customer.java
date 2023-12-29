package ms.bc48.account.ini.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
    private String id;
    private String name;
    private String lastName;
    private String numberDocument;
    private TypePerson typePerson;
	
}
