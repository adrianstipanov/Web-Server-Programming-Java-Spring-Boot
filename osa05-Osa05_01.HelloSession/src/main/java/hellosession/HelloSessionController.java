package hellosession;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSessionController {
    
    @RequestMapping("*")
    @ResponseBody
    public String sayHello(HttpSession session) {
        int visits = 0;

        if (session.getAttribute("visits") != null) {
            visits = (int) session.getAttribute("visits");
            return "Hello again!";
        }
        visits++;
        session.setAttribute("visits", visits);
        return "Hello there!";
    }
}
