package ms.bc48.account.ini.repository;

import java.util.Date;

import org.springframework.data.repository.reactive.RxJava3CrudRepository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import ms.bc48.account.ini.model.Account;

public interface AccountRepository extends RxJava3CrudRepository<Account, String>{

	Flowable<Account> findByCustomerIdAndTypeAccountAccountTypeCode(String idCustomer,String accountTypeCode);
	Maybe<Account> findByCustomerIdAndTypeAccountIdAndNumberAccount(String idCustomer,String idTypeAccount,String numberAccount);
	Maybe<Account> findByCustomerIdAndNumberAccountAndPinAndExpirationDate(String idCustomer,String numberAccount,Integer pin,Date expirationDate);
	Maybe<Account> findByPin(Integer pin);
}
