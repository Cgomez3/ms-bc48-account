package ms.bc48.account.ini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.rxjava3.core.Maybe;
import ms.bc48.account.ini.model.Account;
import ms.bc48.account.ini.model.ParamRequest;
import ms.bc48.account.ini.service.AccountService;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(value = {"*"})
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Account account) {
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
				.body(accountService.save(account));

	}
	
	@GetMapping("/pru")
	public String getdata() {
		return "adasdasdasdasddadasd";
	}
	
	@PostMapping("/get-account")
	public ResponseEntity<?> getAccount(@RequestBody ParamRequest paramRequest) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(accountService.findByCustomerIdAndNumberAccountAndExpirationDateAndPin(paramRequest.getIdCustomer(), paramRequest.getNumberAccount(), paramRequest.getExpirationDate(), paramRequest.getPin()));

	}
	
	@PostMapping("/getaccount")
	public Maybe<Account> getAccount2(@RequestBody ParamRequest paramRequest) {
		return accountService.findByCustomerIdAndNumberAccountAndExpirationDateAndPin(paramRequest.getIdCustomer(), paramRequest.getNumberAccount(), paramRequest.getExpirationDate(), paramRequest.getPin());

	}
	
	@GetMapping("/pin/{pin}")
	public ResponseEntity<?> getAccountByPin(@PathVariable Integer pin) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(accountService.findByPin(pin));

	}

	/*@GetMapping("/{idAccount}")
	public ResponseEntity<?> findDocument(@PathVariable String idAccount) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(accountService.findByCustomerId(idAccount));

	}*/

}
