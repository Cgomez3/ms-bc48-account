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
import ms.bc48.account.ini.model.AccountType;
import ms.bc48.account.ini.service.AccountTypeService;

@RestController
@RequestMapping("/api/type-account")
@CrossOrigin(value = {"*"})
public class AccountTypeController {
	
	@Autowired
	private AccountTypeService typeAccountService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AccountType typeAccount){
	    return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(typeAccountService.save(typeAccount));
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
	    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(typeAccountService.findAll());
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
	    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(typeAccountService.findById(id));
		
	}

}
