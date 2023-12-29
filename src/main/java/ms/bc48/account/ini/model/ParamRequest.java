package ms.bc48.account.ini.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamRequest {
	
	private String numberAccount;
	private String idCustomer;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date expirationDate;
	private Integer pin;
	private BigDecimal amount;
	

}
