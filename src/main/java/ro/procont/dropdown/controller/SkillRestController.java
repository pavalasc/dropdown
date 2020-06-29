package ro.procont.dropdown.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.procont.dropdown.model.Developer;
import ro.procont.dropdown.model.RestResponse;
import ro.procont.dropdown.model.Skill;
import ro.procont.dropdown.repository.DeveloperRepository;
import ro.procont.dropdown.repository.SkillRepository;

@RestController
public class SkillRestController {

	@Autowired
	DeveloperRepository devRepository;

	@Autowired
	SkillRepository skillRepository;

	@RequestMapping("/skills/{developerId}")
	public RestResponse getSkillsForDev(@PathVariable String developerId) throws NumberFormatException {
		//instantiate the response object
		RestResponse response = new RestResponse();

		if (developerId != null) {
			Long devId = Long.parseLong(developerId);
			boolean exists = devRepository.existsById(devId);
			if (exists) {
				Developer developer = devRepository.findById(devId).get();
				List<Skill> skills = developer.getSkills();
				response.setResponseStatus(RestResponse.OK);
				response.setResponse(skills);
			}
		} else {
			response.setResponseStatus(RestResponse.NOT_FOUND);
			response.setResponse("");

		}

		return response;
	}
}
