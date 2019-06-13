package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Category;
import kr.ac.jejunu.polls.domain.CategoryRepository;
import kr.ac.jejunu.polls.domain.Posts;
import kr.ac.jejunu.polls.domain.PostsRepository;
import kr.ac.jejunu.polls.dto.category.CategorySaveRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostDto;
import kr.ac.jejunu.polls.dto.posts.PostsSaveRequestDto;
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
    private PostsService postsService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        categoryRepository.deleteAll();
    }

//    @Test
//    public void Dto_save_on_category() {
//        CategorySaveRequestDto dto = CategorySaveRequestDto.builder()
//                .name("소분류")
//                .build();
//
//        categoryService.save(dto);
//
//        Category category = categoryRepository.findAll().get(0);
//
//        assertThat(category.getName(), is(dto.getName()));
//    }

    @Test
    public void Dto_save_on_posts() {

        PostDto createPost = new PostDto(1l, "테스트 제목",
                "테스트 내용", "테스트 작성자", 1l,
                "테스트 ");
        Posts posts = Posts.builder()
                .title(createPost.getTitle())
                .content(createPost.getContent())
                .author(createPost.getAuthor())
                .category(Category.builder()
                        .name(createPost.getCategoryName())
                        .build())
                .build();

        postsService.createPost(posts);

        assertThat(posts.getAuthor(), is(createPost.getAuthor()));

//        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
//                .author("plenilune@jejunu.ac.kr")
//                .title("테스트 제목")
//                .content("테스트 내용")
//                .categoryId((long) 1)
//                .categoryName("소분류")
//                .build();
//
//        postsService.save(dto);
//
//        Posts posts = postsRepository.findAll().get(0);
//
//        assertThat(posts.getAuthor(), is(dto.getAuthor()));
//        assertThat(posts.getContent(), is(dto.getContent()));
//        assertThat(posts.getTitle(), is(dto.getTitle()));
//        assertThat(posts.getCategory(), is(new Category(1l, "")));

    }
}
