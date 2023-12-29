package ms.bc48.account.ini.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("Headlines")
public class Headline {

	@Id
	private String id;
	private String idCuenta;
	private String idCustomer;
	private boolean major;
	
}
