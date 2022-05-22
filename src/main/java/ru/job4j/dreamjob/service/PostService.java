package ru.job4j.dreamjob.service;


import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostDbStore;

import java.util.Collection;
import java.util.List;


@Service
public class PostService {

    private final PostDbStore store;

    private final CityService cityService;

    public PostService(PostDbStore store, CityService cityService) {
        this.store = store;
        this.cityService = cityService;
    }

    public Post findPostById(int id) {
        return store.findById(id);
    }

    public void addPost(Post post) {
        store.add(post);
    }

    public Collection<Post> findAllPosts() {
        return store.findAll();
    }

    public List<Post> findAllCities() {
        List<Post> posts = store.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return posts;
    }

    public void updatePost(Post post) {
        store.update(post);
    }

}

