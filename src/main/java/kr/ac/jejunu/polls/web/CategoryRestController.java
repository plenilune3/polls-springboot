package kr.ac.jejunu.polls.web;

import kr.ac.jejunu.polls.dto.category.CategorySaveRequestDto;
import kr.ac.jejunu.polls.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryRestController {

    private CategoryService categoryService;

    @PostMapping("/save")
    public void saveCategory(@RequestBody CategorySaveRequestDto dto) {
        categoryService.save(dto);
    }

}
