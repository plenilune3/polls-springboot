package kr.ac.jejunu.polls.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryFindRequestDto {

    private Long id;

    @Builder
    public CategoryFindRequestDto(Long id) {
        this.id = id;
    }
}
