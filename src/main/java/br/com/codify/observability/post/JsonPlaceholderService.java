package br.com.codify.observability.post;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface JsonPlaceholderService {

    @GetExchange("/api/posts")
    List<Post> findAll();

    @GetExchange("/api/posts/{id}")
    Post findById(@PathVariable Integer id);
}