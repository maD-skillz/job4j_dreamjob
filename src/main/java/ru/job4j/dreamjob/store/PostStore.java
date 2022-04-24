package ru.job4j.dreamjob.store;


import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class PostStore {
    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger ids = new AtomicInteger(4);

    public PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Junior Java vacancy", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "Middle Java vacancy", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "Senior Java vacancy", LocalDateTime.now()));
    }

    public static PostStore instOf() {
        return INST;
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
        for(Integer index : posts.keySet()) {
            if (index.equals(id)) {
                return posts.get(index);
            }
        }
        return null;
    }

    public Post update(Post post) {
      Post postToFind = findById(post.getId());
      postToFind.setName(post.getName());
      postToFind.setDescription(post.getDescription());
      postToFind.setCreated(LocalDateTime.now());
      return postToFind;
    }

}
