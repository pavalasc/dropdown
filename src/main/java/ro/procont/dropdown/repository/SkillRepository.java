package ro.procont.dropdown.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.procont.dropdown.model.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {
	public List<Skill> findByLabel(String label);
}
