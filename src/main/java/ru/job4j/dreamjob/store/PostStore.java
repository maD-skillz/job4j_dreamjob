package ru.job4j.dreamjob.store;


import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger ids = new AtomicInteger(4);

    public PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Junior Java vacancy", LocalDateTime.now()
                , new City(1, "Moscow")));
        posts.put(2, new Post(2, "Middle Java Job", "Middle Java vacancy", LocalDateTime.now()
                , new City(2, "New York")));
        posts.put(3, new Post(3, "Senior Java Job", "Senior Java vacancy", LocalDateTime.now()
                , new City(3, "St.Petersburg")));
    }


    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(ids.incrementAndGet());
        post.setCreated(LocalDateTime.now());
        posts.putIfAbsent(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
      posts.replace(post.getId(), post);
    }

}
