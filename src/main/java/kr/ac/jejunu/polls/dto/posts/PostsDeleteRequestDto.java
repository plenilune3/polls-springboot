package kr.ac.jejunu.polls.dto.posts;

import kr.ac.jejunu.polls.domain.Posts;
import kr.ac.jejunu.polls.domain.PostsRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsDeleteRequestDto {
    private Long id;

    @Builder
    public PostsDeleteRequestDto(Long id) {
        this.id = id;
    }

}
