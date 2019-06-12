package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.domain.CategoryRepository;
import kr.ac.jejunu.polls.dto.posts.CategorySaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void cleanup() {
        categoryRepository.deleteAll();
    }

    @Test
    public void Dto_save_on_category() {
        CategorySaveRequestDto dto = CategorySaveRequestDto.builder()
                .name("소분류")
                .build();

        categoryService.save(dto);

        Category category = categoryRepository.findAll().get(0);

        assertThat(category.getName(), is(dto.getName()));
    }
}
