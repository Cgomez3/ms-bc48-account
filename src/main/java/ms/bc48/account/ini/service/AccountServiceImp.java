package ms.bc48.account.ini.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.netty.handler.codec.http.HttpContentEncoder.Result;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import ms.bc48.account.ini.config.ConfigException;
import ms.bc48.account.ini.model.Account;
import ms.bc48.account.ini.repository.AccountRepository;
import ms.bc48.account.ini.repository.HeadlineRepository;
import ms.bc48.account.ini.utils.Constants;

@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private HeadlineRepository headlineRepository;

	@Override
	public Single<Account> save(Account account) {
		validPerson(account);
		validCompany(account);
		return Maybe.just(account)
				.flatMapSingle(
						acc -> accountRepository
								.findByCustomerIdAndTypeAccountIdAndNumberAccount(account.getCustomer().getId(),
										account.getTypeAccount().getId(), account.getNumberAccount())
								.flatMapSingle(accVal -> {
									return Single.error(new ConfigException("the account number already exists"));
								}).switchIfEmpty(accountRepository.findByPin(acc.getPin()).flatMapSingle(accPin -> {
									return Single.error(new ConfigException("the pin number already exists"));
								}).switchIfEmpty(
										accountRepository.save(acc)
												.filter(accounp -> accounp.getCustomer().getTypePerson().getCodigo()
														.equalsIgnoreCase(Constants.COD_COMPANY))
												.flatMapSingle(accfin -> {
													return Maybe.just(accfin).flatMapSingle(accreturn -> {
														if (acc.getHeadlines() != null) {
															acc.getHeadlines().stream().forEach(h -> {
																h.setIdCuenta(accfin.getId());
															});
															headlineRepository.saveAll(acc.getHeadlines()).subscribe();
														}
														return Single.just(accreturn);
													}).switchIfEmpty(Single.fromCallable(() -> accfin));

												}).switchIfEmpty(Single.just(acc)))))
				.switchIfEmpty(Single.error(new ConfigException("An error occurred while trying to create an item")))
				.doOnError(e -> Single.error(new ConfigException("An error occurred while trying to create an item")))
				.cast(Account.class);
	}


	@Async
	private void validPerson(Account account) throws ConfigException {
		Long totalAccountType = accountRepository
				.findByCustomerIdAndTypeAccountAccountTypeCode(account.getCustomer().getId(),
						account.getTypeAccount().getAccountTypeCode())
				.count().blockingGet();
		if (account.getCustomer().getTypePerson().getCodigo().equalsIgnoreCase(Constants.COD_PERSON)) {
			if (account.getTypeAccount().getAccountTypeCode().equalsIgnoreCase(Constants.SAVINGS_ACCOUNT)) {
				if (totalAccountType.intValue() >= Constants.MAXIMUN_SAVINGS_ACCOUNT_NUMBER) {
					throw new ConfigException("The Person has exceeded the maximum number of accounts");
				}
			}
			if (account.getTypeAccount().getAccountTypeCode().equalsIgnoreCase(Constants.CURRENT_ACCOUNT)
					|| account.getTypeAccount().getAccountTypeCode().equalsIgnoreCase(Constants.FIXED_TERM)) {
				if (totalAccountType.intValue() >= Constants.ONLY) {
					throw new ConfigException("The person must only have a checking or fixed-term account");
				}
			}

			if (account.getTypeAccount().getAccountTypeCode().equalsIgnoreCase(Constants.CREDIT_PERSON)) {
				if (totalAccountType.intValue() >= Constants.ONLY) {
					throw new ConfigException("A customer can only have one credit product");
				}
			}
		}
	}

	@Async
	private void validCompany(Account account) throws ConfigException {
		if (account.getCustomer().getTypePerson().getCodigo().equalsIgnoreCase(Constants.COD_COMPANY)) {
			if (account.getTypeAccount().getAccountTypeCode().equalsIgnoreCase(Constants.SAVINGS_ACCOUNT)
					|| account.getTypeAccount().getAccountTypeCode().equalsIgnoreCase(Constants.FIXED_TERM)) {
				throw new ConfigException("The company cannot have a savings or fixed-term account");
			}

			if (account.getTypeAccount().getAccountTypeCode().equalsIgnoreCase(Constants.CREDIT_COMPANY)) {
				Long totalAccountType = accountRepository
						.findByCustomerIdAndTypeAccountAccountTypeCode(account.getCustomer().getId(),
								account.getTypeAccount().getAccountTypeCode())
						.count().blockingGet();
				if (totalAccountType.intValue() >= Constants.ONLY) {
					throw new ConfigException("A customer can only have one credit product");
				}
			}
		}
	}

	@Async
	private void validPin(Account account) throws ConfigException {
		/*
		 * accountRepository.findByPin(account.getPin()) .map( c -> {
		 * 
		 * throw new ConfigException("A customer can only have one credit product");
		 * 
		 * }).forEach(i -> System.err.println(i.toString()));
		 */

	}

	@Override
	public Maybe<Account> findByCustomerIdAndTypeAccountIdAndNumberAccount(String idCustomer, String idTypeAccount,
			String numberAccount) {
		return accountRepository.findByCustomerIdAndTypeAccountIdAndNumberAccount(idCustomer, idTypeAccount,
				numberAccount);
	}
	/*
	 * @Override public Flowable<Account> findByCustomerId(String idCustomer) {
	 * return accountRepository.findByCustomerId(idCustomer); }
	 */

	@Override
	public Flowable<Account> findByCustomerIdAndTypeAccountAccountTypeCode(String idCustomer, String accountTypeCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Maybe<Account> findByCustomerIdAndNumberAccountAndExpirationDateAndPin(String idCustomer,
			String numberAccount, Date expirationDate, Integer pin) {
		return accountRepository.findByCustomerIdAndNumberAccountAndPinAndExpirationDate(idCustomer, numberAccount,pin,expirationDate);
	}
	/*
	 * @Override public Flowable<Account> findByPin(Integer pin) { return
	 * accountRepository.findByPin(pin); }
	 */

	@Override
	public Maybe<Account> findByPin(Integer pin) {
		// TODO Auto-generated method stub
		return null;
	}
}
