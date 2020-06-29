package ro.procont.dropdown.repository;

import org.springframework.data.repository.CrudRepository;

import ro.procont.dropdown.model.Developer;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {

}
