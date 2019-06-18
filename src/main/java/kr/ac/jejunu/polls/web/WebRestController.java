package kr.ac.jejunu.polls.web;

import kr.ac.jejunu.polls.domain.Posts;
import kr.ac.jejunu.polls.domain.PostsRepository;
import kr.ac.jejunu.polls.dto.posts.PostsDeleteRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsSaveRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsUpdateRequestDto;
import kr.ac.jejunu.polls.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;
    private PostsService postsService;

    @GetMapping("hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Posts savePosts(@RequestBody PostsSaveRequestDto dto) {
        Posts posts = postsService.save(dto);
        return posts;
    }

    @PostMapping("/delete")
    public void deletePosts(@RequestBody PostsDeleteRequestDto dto) {
        postsRepository.deleteById(dto.getId());
    }

    @PostMapping("/update")
    public void updatePosts(@RequestBody PostsUpdateRequestDto dto) {
        postsService.update(dto);
    }


}
