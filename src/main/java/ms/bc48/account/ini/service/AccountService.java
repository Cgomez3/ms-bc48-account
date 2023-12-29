package ms.bc48.account.ini.service;

import java.util.Date;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import ms.bc48.account.ini.model.Account;

public interface AccountService {
	Single<Account> save(Account account);
	Maybe<Account> findByCustomerIdAndTypeAccountIdAndNumberAccount(String idCustomer,String idTypeAccount,String numberAccount);
	Flowable<Account> findByCustomerIdAndTypeAccountAccountTypeCode(String idCustomer,String accountTypeCode);
	Maybe<Account> findByCustomerIdAndNumberAccountAndExpirationDateAndPin(String idCustomer,String numberAccount,Date expirationDate,Integer pin);
	Maybe<Account> findByPin(Integer pin);
}
