package scoreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/games")
    public Game createGame(@RequestBody Game game) {
        return this.gameRepository.save(game);
    }

    @GetMapping("/games")
    public List<Game> getGames() {
        return this.gameRepository.findAll();
    }

    @GetMapping("/games/{name}")
    public Game getGame(@PathVariable String name){
        return this.gameRepository.findByName(name);
    }

    @DeleteMapping("/games/{name}")
    public Game deleteByName(@PathVariable String name){
        Game game = this.gameRepository.findByName(name);
        this.gameRepository.delete(game);
        return game;
    }

}


