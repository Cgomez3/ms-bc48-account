package ms.bc48.account.ini.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Accounts")
public class Account {
	
	@Id
	private String id;
	private Integer correlativo;
	private String numberAccount;
	private AccountType typeAccount;
	private Customer customer;
	private BigDecimal allocatedAmount;
	private BigDecimal balance;
	private boolean isPassive;
	private boolean isOwner;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date expirationDate;
	private Integer pin;
	@Transient
	private List<Headline> headlines;
	

}
