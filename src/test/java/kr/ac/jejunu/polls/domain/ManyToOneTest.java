package kr.ac.jejunu.polls.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToOneTest {

    @Autowired
    private CategoryRepository cr;

    @Autowired
    private PostsRepository pr;

    @After
    public void cleanup() {
        pr.deleteAll();
        cr.deleteAll();
    }

    @Test
    public void ManyToOne() {

        Category first = Category.builder()
                .name("first").build();
        Category second = Category.builder()
                .name("second").build();
        Category third = Category.builder()
                .name("third").build();

        cr.save(first);
        cr.save(second);
        cr.save(third);

        Posts post = Posts.builder()
                .author("테스트 작성자")
                .title("테스트 제목")
                .content("테스트 내용")
                .category(second)
                .build();

        pr.save(post);

        List<Posts> posts = pr.findAll();

        for (Posts e : posts) {
            System.out.println(e.toString() + " " + e.getCategory().toString());
        }
    }
}
