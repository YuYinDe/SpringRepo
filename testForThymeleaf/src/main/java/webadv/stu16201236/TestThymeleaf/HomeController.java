package webadv.stu16201236.TestThymeleaf;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// inject via application.properties
    @Value("${welcome.message}")
    private String message;
	
    private List<String> tasks = Arrays.asList("Git", "Maven", "Gradle", "Spring", "Bootstrap", "jQuery", "MyBatis","Redis");
    
    @GetMapping("/")
	public String index(Model model) {
    	model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);
		return "welcome";		
	}
}

