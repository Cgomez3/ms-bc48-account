package ms.bc48.account.ini.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import ms.bc48.account.ini.model.AccountType;

public interface AccountTypeService{
	Single<AccountType> save(AccountType typeAccount);
	Maybe<AccountType> findByNameTypeAccount(String nameTypeAccount);
	Flowable<AccountType> findAll();
	Maybe<AccountType> findById(String id);
}
