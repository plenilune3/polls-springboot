package kr.ac.jejunu.polls.dto.posts;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.domain.CategoryRepository;
import kr.ac.jejunu.polls.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
    private Category category;
    private String pollAddress;

    @Builder
    public PostsSaveRequestDto(String title, String content,
                               String author, Category category,
                               String pollAddress) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.pollAddress = pollAddress;
    }

    public Posts toEntity() {

        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .category(category)
                .pollAddress(pollAddress)
                .build();
    }
}
