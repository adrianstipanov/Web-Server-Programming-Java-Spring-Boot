package gifbin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class GifController {

    @Autowired
    FileObjectRepository fileObjectRepository;

    @GetMapping("/gifs")
    public String redirectToGif() {
        return "redirect:/gifs/1";
    }

    @GetMapping("/gifs/{id}")
    public String getFiles(Model model, @PathVariable Long id) {
        long count = fileObjectRepository.count();
        model.addAttribute("count", count);

        if (id >= 1 && id < count) {
            model.addAttribute("next", id + 1);
        }

        if (id > 1 && id <= count) {
            model.addAttribute("previous", id - 1);
        }

        if (id >= 1 && id <= count) {
            model.addAttribute("current", id);
        }

        return "gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return fileObjectRepository.getOne(id).getContent();
    }

    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getContentType());
        if (file.getContentType().equals("image/gif")) {
            FileObject fo = new FileObject();
            fo.setContent(file.getBytes());

            fileObjectRepository.save(fo);
        }
        return "redirect:/gifs";
    }
}
