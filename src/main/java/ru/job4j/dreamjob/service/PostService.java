package ru.job4j.dreamjob.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;


import java.util.Collection;


@Service
public class PostService {

    private final PostStore store;

    public PostService(PostStore store) {
       this.store = store;
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

    public void updatePost(Post post) {
        store.update(post);
    }

}

