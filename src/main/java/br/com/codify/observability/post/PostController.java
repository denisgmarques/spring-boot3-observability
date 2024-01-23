package br.com.codify.observability.post;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final JsonPlaceholderService jsonPlaceholderService;

    public PostController(JsonPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    @GetMapping
    public List<Post> findAll() {
        return jsonPlaceholderService.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(String id) {
        return jsonPlaceholderService.findById(Integer.parseInt(id));
    }
}
