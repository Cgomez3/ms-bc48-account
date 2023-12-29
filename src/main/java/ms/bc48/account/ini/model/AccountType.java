package ms.bc48.account.ini.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("TypeAccounts")
public class AccountType {
    @Id
	private String id;
    private String accountTypeCode;
	private String nameTypeAccount;
	private boolean pasivo;
}
