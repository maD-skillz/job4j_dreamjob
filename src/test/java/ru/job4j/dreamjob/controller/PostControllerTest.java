package ru.job4j.dreamjob.controller;


import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.testng.annotations.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostControllerTest {
    @Test
    public void whenFindAllPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post", "Post desc", LocalDateTime.now(), new City(1, "Vorkuta")),
                new Post(2, "New post2", "Post desc", LocalDateTime.now(), new City(2, "Boston"))
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAllPosts()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.posts(model, new MockHttpSession());
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }


    @Test
    public void whenCreatePost() throws IOException {
        Post input = new Post(1, "New post", "Post desc", LocalDateTime.now(), new City(1, "Vorkuta"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.createPost(input, new MockMultipartFile("name", new byte[]{}));
        verify(postService).addPost(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFindPostById() {
        Post post1 = new Post(1, "New post", "Post desc", LocalDateTime.now(), new City(1, "Vorkuta"));
        Post post2 = new Post(2, "New post2", "Post desc", LocalDateTime.now(), new City(2, "Boston"));
        Post post3 = new Post(3, "New post3", "Post desc", LocalDateTime.now(), new City(1, "Vorkuta"));

        PostService postService = mock(PostService.class);

        postService.addPost(post1);
        postService.addPost(post2);
        postService.addPost(post3);
        when(postService.findPostById(2)).thenReturn(post2);
        assertThat(postService.findPostById(2), is(post2));
    }

    @Test
    public void whenUpdatePost() {
        Post oldPost =
                new Post(1, "Old post", "Old Post desc", LocalDateTime.now(), new City(1, "Vorkuta"));
        Post updPost =
                new Post(1, "New updated post", "Updated Post desc", LocalDateTime.now(), new City(2, "Boston"));

        PostService postService = mock(PostService.class);
        postService.addPost(oldPost);
        postService.updatePost(updPost);

        when(postService.findPostById(1)).thenReturn(updPost);
        assertThat(postService.findPostById(oldPost.getId()).getName(), is(updPost.getName()));
    }

}