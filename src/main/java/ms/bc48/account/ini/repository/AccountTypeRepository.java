package ms.bc48.account.ini.repository;

import org.springframework.data.repository.reactive.RxJava3CrudRepository;

import io.reactivex.rxjava3.core.Maybe;
import ms.bc48.account.ini.model.AccountType;

public interface AccountTypeRepository extends RxJava3CrudRepository<AccountType, String>{
   Maybe<AccountType> findByNameTypeAccount(String nameTypeAccount);
}
