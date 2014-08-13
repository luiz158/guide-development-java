import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eprogramar.springjpa.model.Person;
import com.eprogramar.springjpa.service.PersonService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private PersonService personService;

	@Autowired
	@Qualifier("personService")
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public PersonService getPersonDAOImpl() {
		return this.personService;
	}	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("home()...");
		ModelAndView mv = new ModelAndView("home");
		
		this.personService.persist( new Person("Fabiano", "123") );
		
		List<Person> listAll = this.personService.getAll();
		for (Person person : listAll) {
			System.out.println(person);
		}
		
		return mv;
	}
}
