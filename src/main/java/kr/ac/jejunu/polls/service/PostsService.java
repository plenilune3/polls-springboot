package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Posts;
import kr.ac.jejunu.polls.domain.PostsRepository;
import kr.ac.jejunu.polls.dto.posts.PostsDeleteRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsSaveRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class PostsService {
    private PostsRepository postsRepository;

    public Posts save(PostsSaveRequestDto dto) {
        Posts posts = postsRepository.save(dto.toEntity());
        return posts;
    }

    public void delete(PostsDeleteRequestDto dto) {
        postsRepository.deleteById(dto.getId());
    }

    public void update(PostsUpdateRequestDto dto) {
        Posts posts = postsRepository.getOne(dto.getId());
        posts.setTitle(dto.getTitle());
        posts.setAuthor(dto.getAuthor());
        posts.setContent(dto.getContent());
        posts.setCategory(dto.getCategory());
        postsRepository.save(posts);
    }

    public List<Posts> read() {
        return postsRepository.findAll();
    }

}