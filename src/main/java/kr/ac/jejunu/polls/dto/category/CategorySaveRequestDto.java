package kr.ac.jejunu.polls.dto.category;

import kr.ac.jejunu.polls.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {

    private Long id;
    @NotBlank
    private String name;

    @Builder
    public CategorySaveRequestDto(String name) {
        this.name = name;
    }

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .build();
    }

}
