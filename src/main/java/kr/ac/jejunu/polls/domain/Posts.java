package kr.ac.jejunu.polls.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    private String address;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @Builder
    public Posts(String title, String content,
                 String author, Category category, String address) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.address = address;
    }

    @Override
    public String toString() {
        String result = "[post_" + id + "] " + title + " " + author + " " + content;
        return result;
    }

}
