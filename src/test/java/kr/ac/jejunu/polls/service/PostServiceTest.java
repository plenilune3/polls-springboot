package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Posts;
import kr.ac.jejunu.polls.domain.PostsRepository;
import kr.ac.jejunu.polls.dto.posts.PostsDeleteRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsSaveRequestDto;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto_save_on_posts() {
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("plenilune@jejunu.ac.kr")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        postsService.save(dto);

        Posts posts = postsRepository.findAll().get(0);

        assertThat(posts.getAuthor(), is(dto.getAuthor()));
        assertThat(posts.getContent(), is(dto.getContent()));
        assertThat(posts.getTitle(), is(dto.getTitle()));
    }

    @Test
    public void Dto_delete_on_posts() {
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("plenilune@jejunu.ac.kr")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        postsService.save(dto);

        Posts posts = postsRepository.findAll().get(0);

        assertThat(posts.getAuthor(), is(dto.getAuthor()));
        assertThat(posts.getContent(), is(dto.getContent()));
        assertThat(posts.getTitle(), is(dto.getTitle()));

        PostsDeleteRequestDto dto1 = PostsDeleteRequestDto.builder()
                .id(1l).build();

        postsService.delete(dto1);

        boolean post = postsRepository.findAll().isEmpty();

        assertThat(post, is(true));

    }
}
