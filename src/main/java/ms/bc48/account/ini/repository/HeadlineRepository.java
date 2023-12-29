package ms.bc48.account.ini.repository;

import org.springframework.data.repository.reactive.RxJava3CrudRepository;

import ms.bc48.account.ini.model.Headline;

public interface HeadlineRepository extends RxJava3CrudRepository<Headline, String>{

}
