package todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class TodoApplicationController {
    @Autowired
    private TodoRepository tasks;

    @GetMapping("/")
    public String listTasks(Model model){
        model.addAttribute("items", tasks.findAll());
        return "index";
    }

    @PostMapping("/")
    public String addTask(@RequestParam String name){
        Item item = new Item(name, 0);
        this.tasks.save(item);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String listItem(Model model, @PathVariable Long id){
        Item item = this.tasks.getOne(id);
        item.setChecked(item.getChecked()+1);
        this.tasks.save(item);
        model.addAttribute("item", item);

        return "todo";
        }

}
