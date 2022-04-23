package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.time.LocalDateTime;

@Controller
public class PostController {

    private final PostStore store = PostStore.instOf();


    @GetMapping("/addPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post(0, "Заполните поле", "Заполните текст", LocalDateTime.now()));
        return "addPost";
    }

    @GetMapping("/formAddPost")
    public String formAddPost(Model model) {
        return "addPost";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", store.findAll());
        return "posts";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        store.add(post);
        return "redirect:/posts";
    }

}


