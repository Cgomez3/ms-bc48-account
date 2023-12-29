package ms.bc48.account.ini.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypePerson {
    private String id;
    @NotBlank
	private String codigo;
    @NotBlank
    private String description;
    
    
}
