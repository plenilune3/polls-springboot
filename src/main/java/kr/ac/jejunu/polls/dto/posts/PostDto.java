package kr.ac.jejunu.polls.dto.posts;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PostDto {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String author;

    @NotNull
    private Long categoryId;

    private String categoryName;
}
