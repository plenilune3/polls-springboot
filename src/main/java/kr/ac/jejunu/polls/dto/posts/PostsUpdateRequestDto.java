package kr.ac.jejunu.polls.dto.posts;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Category category;

    @Builder
    public  PostsUpdateRequestDto(Long id, String title, String content,
                                  String author, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .category(category)
                .build();
    }
}
