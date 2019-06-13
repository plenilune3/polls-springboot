package kr.ac.jejunu.polls.dto.posts;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
    @NotNull
    private Long categoryId;
    private String categoryName;

    @Builder
    public PostsSaveRequestDto(String title, String content,
                               String author, Long categoryId, String categoryName) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .category(Category.builder()
                        .name(categoryName).build())
                .build();
    }
}
