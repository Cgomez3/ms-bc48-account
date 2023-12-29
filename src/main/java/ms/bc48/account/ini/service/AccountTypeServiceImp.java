package ms.bc48.account.ini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import ms.bc48.account.ini.config.ConfigException;
import ms.bc48.account.ini.model.AccountType;
import ms.bc48.account.ini.repository.AccountTypeRepository;

@Service
public class AccountTypeServiceImp implements AccountTypeService{

	@Autowired
	private AccountTypeRepository typeAccountRepository;
	
	@Override
	public Single<AccountType> save(AccountType typeAccount) {
		
		return typeAccountRepository.findByNameTypeAccount(typeAccount.getNameTypeAccount())
				.map(t -> {
					throw new ConfigException("the account type is already registered");
				}).cast(AccountType.class)
				.switchIfEmpty(typeAccountRepository.save(typeAccount));
	}

	@Override
	public Maybe<AccountType> findByNameTypeAccount(String nameTypeAccount) {
		return typeAccountRepository.findByNameTypeAccount(nameTypeAccount);
	}

	@Override
	public Flowable<AccountType> findAll() {
		return typeAccountRepository.findAll();
	}

	@Override
	public Maybe<AccountType> findById(String id) {
		return typeAccountRepository.findById(id);
	}

}
