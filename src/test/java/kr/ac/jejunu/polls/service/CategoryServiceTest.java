package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.domain.CategoryRepository;
import kr.ac.jejunu.polls.domain.Posts;
import kr.ac.jejunu.polls.domain.PostsRepository;
import kr.ac.jejunu.polls.dto.category.CategoryFindRequestDto;
import kr.ac.jejunu.polls.dto.category.CategorySaveRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostDto;
import kr.ac.jejunu.polls.dto.posts.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostsService postsService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        categoryRepository.deleteAll();
        ;
        postsRepository.deleteAll();
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

    @Test
    public void Dto_save_on_posts() {

//        Optional<Category> categoryOptional = categoryRepository.findById(1l);
//        if(categoryOptional.isEmpty()) return;
//
//        Category category = categoryOptional.get();

        CategoryFindRequestDto dto1 = CategoryFindRequestDto.builder()
                .id(1l)
                .build();
        Category category = categoryService.find(dto1);

        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("테스트 제목")
                .author("테스트 작성자")
                .content("테스트 내용")
                .category(category)
                .build();

        postsService.save(dto);

        Posts posts = postsRepository.findAll().get(0);

        assertThat(posts.getAuthor(), is(dto.getAuthor()));
        assertThat(posts.getTitle(), is(dto.getTitle()));
        assertThat(posts.getContent(), is(dto.getContent()));
        assertThat(posts.getCategory().getName(), is(dto.getCategory().getName()));

    }
}
