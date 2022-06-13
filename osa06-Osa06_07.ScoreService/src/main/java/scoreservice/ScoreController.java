package scoreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    GameRepository gameRepository;

    @PostMapping("/games/{name}/scores")
    public Score setScore(@RequestBody Score score, @PathVariable String name) {
        Game game =  gameRepository.findByName(name);
        score.setGame(game);
        return scoreRepository.save(score);
    }

    @GetMapping("/games/{name}/scores")
    public List<Score> getScores(@PathVariable String name) {
        return this.scoreRepository.findByGame(this.gameRepository.findByName(name));
    }

    @GetMapping("/games/{name}/scores/{id}")
    public Score getScoresByNameAndId(@PathVariable String name, @PathVariable Long id) {
        Game game = this.gameRepository.findByName(name);
        return this.scoreRepository.findByGameAndId(game, id);
    }

    @DeleteMapping("/games/{name}/scores/{id}")
    public Game deleteByNameAndId(@PathVariable String name, @PathVariable Long id) {
        Game game = this.gameRepository.findByName(name);
        this.scoreRepository.delete(this.scoreRepository.findByGameAndId(game, id));

        return game;
    }

}

