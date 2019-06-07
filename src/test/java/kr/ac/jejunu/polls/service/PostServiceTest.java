package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.Posts;
import kr.ac.jejunu.polls.domain.PostsRepository;
import kr.ac.jejunu.polls.dto.posts.PostsDeleteRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsSaveRequestDto;
import kr.ac.jejunu.polls.dto.posts.PostsUpdateRequestDto;
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

    @Test
    public void Dto_update_on_posts() {
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

        PostsUpdateRequestDto dto1 = PostsUpdateRequestDto.builder()
                .id(2l)
                .author("수정")
                .content("수정")
                .title("수정")
                .build();

        postsService.update(dto1);

        Posts posts1 = postsRepository.findAll().get(0);

        assertThat(posts1.getAuthor(), is(dto1.getAuthor()));
        assertThat(posts1.getContent(), is(dto1.getContent()));
        assertThat(posts1.getTitle(), is(dto1.getTitle()));
    }
}
