package kr.ac.jejunu.polls.dto.poll;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VoteRequestDto {
    private String fromAddress;
    private String password;
    private String pollAddress;
    private int vote;
}
