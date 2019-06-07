package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Posts;
import kr.ac.jejunu.polls.domain.PostsRepository;
import kr.ac.jejunu.polls.dto.posts.PostsDeleteRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsSaveRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public void delete(PostsDeleteRequestDto dto) {
        postsRepository.deleteById(dto.getId());
    }

    @Transactional
    public void update(PostsUpdateRequestDto dto) {
        Posts posts = postsRepository.getOne(dto.getId());
        posts.setTitle(dto.getTitle());
        posts.setAuthor(dto.getAuthor());
        posts.setContent(dto.getContent());
        postsRepository.save(posts);
    }
}
