package ro.procont.dropdown.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.procont.dropdown.exception.EntityNotFoundException;
import ro.procont.dropdown.model.Developer;
import ro.procont.dropdown.model.RestResponse;
import ro.procont.dropdown.model.Skill;
import ro.procont.dropdown.repository.DeveloperRepository;
import ro.procont.dropdown.repository.SkillRepository;

import org.springframework.stereotype.Controller;

@Controller
public class DevelopersController {

	@Autowired
	DeveloperRepository devRepository;

	@Autowired
	SkillRepository skillRepository;
	
	@RequestMapping("/")
	public String getIndex( Model model) {
         return "index";
	}
	
	@RequestMapping("/dependent")
	public String getDependent( Model model) {
		model.addAttribute("developers", devRepository.findAll());
        model.addAttribute("skills", null);
         return "dependent";
	}
	
	@PostMapping("/dependent")
    public String saveFromDependendPage(@RequestParam Optional<String> developerId, @RequestParam Optional<String> skillId, Model model) throws NumberFormatException, EntityNotFoundException {
		if (developerId != null) {
			System.out.println("--> @RequestParam String developerId : " + developerId);
			System.out.println("--> @RequestParam String skillId : " + skillId);
			Long devId = Long.parseLong(developerId.get());
			Developer developer = devRepository.findById(devId).orElseThrow(() -> new EntityNotFoundException("Invalid developer id:" + developerId));;//.get();
			System.out.println("--> Selected developer: " + developer);
	        model.addAttribute("developer", developer);
	        Long skillDevId = Long.parseLong(skillId.get());
	        Skill selectedSkill = skillRepository.findById(skillDevId).orElseThrow(() -> new EntityNotFoundException("Invalid skill id:" + skillId));//.get();
	        model.addAttribute("selectedSkill", selectedSkill);
	        return "saved";
		}
		else {
			return "redirect:/dependent";
		}
    }
	
	

	@RequestMapping("/developer/{id}")
	public String developer(@PathVariable Long id, Model model) {
        model.addAttribute("developer", devRepository.findById(id).get());
        model.addAttribute("skills", skillRepository.findAll());
        return "developer";
	}
	
	

    @RequestMapping(value="/developers",method=RequestMethod.GET) //get all and form to add new
	public String developersList(Model model) {
        model.addAttribute("developers", devRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "developers";
	}

    @RequestMapping(value="/developers",method=RequestMethod.POST)
	public String developersAdd(@RequestParam String email, 
						@RequestParam String firstName, @RequestParam String lastName, String skillId, Model model) {
        Developer newDeveloper = new Developer();
        newDeveloper.setEmail(email);
        newDeveloper.setFirstName(firstName);
        newDeveloper.setLastName(lastName);
        Long id = Long.parseLong(skillId);
        Skill skill = skillRepository.findById(id).get();
       // System.out.println("--> skillId: " + skillRepository.findById(id));
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);
        newDeveloper.setSkills(skills);
        devRepository.save(newDeveloper);

        model.addAttribute("developer", newDeveloper);
        model.addAttribute("skills", skillRepository.findAll());
        return "redirect:/developer/" + newDeveloper.getId();
	}

    @RequestMapping(value="/developer/{id}/skills", method=RequestMethod.POST)
	public String developersAddSkill(@PathVariable Long id, @RequestParam Long skillId, Model model) {
    	Skill skill = skillRepository.findById(skillId).get();
    	Developer developer = devRepository.findById(id).get();

    	if (developer != null) {
    		if (!developer.hasSkill(skill)) {
    			developer.getSkills().add(skill);
    		}
    		devRepository.save(developer);
            model.addAttribute("developer", devRepository.findById(id));
            model.addAttribute("skills", skillRepository.findAll());
            return "redirect:/developer/" + developer.getId();
    	}

        model.addAttribute("developers", devRepository.findAll());
        return "redirect:/developers";
    }

}
