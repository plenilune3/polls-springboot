package kr.ac.jejunu.polls.web;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.dto.category.CategorySaveRequestDto;
import kr.ac.jejunu.polls.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryRestController {

    private CategoryService categoryService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Category saveCategory(@RequestBody CategorySaveRequestDto dto) {
        Category category = categoryService.save(dto);
        return category;
    }

}
