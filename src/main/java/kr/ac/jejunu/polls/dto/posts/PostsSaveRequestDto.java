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
    private String address;

    @Builder
    public PostsSaveRequestDto(String title, String content,
                               String author, Category category,
                               String address) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.address = address;
    }

    public Posts toEntity() {

        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .category(category)
                .address(address)
                .build();
    }
}
