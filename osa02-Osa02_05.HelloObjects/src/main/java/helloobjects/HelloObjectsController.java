package helloobjects;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloObjectsController {

    private List<Item> items;

    public HelloObjectsController() {
        this.items = new ArrayList<>();
        this.items.add(new Item("Wizard hat", "pointy"));
    }

    // Make a method for handling a GET-type request coming to the root path here
    @GetMapping("/")
    public String root(Model model){
        model.addAttribute("items", items);
        return "index";
    }

    // Don't touch this method - we'll learn more later.
    @PostMapping("/")
    public String post(@RequestParam String name, @RequestParam String type) {
        this.items.add(new Item(name.trim(), type.trim()));
        return "redirect:/";
    }

}
