package reloadheroes;

import java.util.UUID;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {
        ReloadStatus reloadStatus = null;
        if (session.getAttribute("name") != null) {
            reloadStatus = reloadStatusRepository.findByName((String) session.getAttribute("name"));
            reloadStatus.setReloads(reloadStatus.getReloads() + 1);
            reloadStatusRepository.save(reloadStatus);
        }
        else {
            reloadStatus = new ReloadStatus();
            String name = RandomStringUtils.randomAlphabetic(5);
            reloadStatus.setName(name);
            reloadStatus.setReloads(1);
            reloadStatusRepository.save(reloadStatus);
            session.setAttribute("name", name);
        }

        model.addAttribute("status", reloadStatus);
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "reloads");
        model.addAttribute("scores", reloadStatusRepository.findAll(pageable));
        return "index";
    }
}
