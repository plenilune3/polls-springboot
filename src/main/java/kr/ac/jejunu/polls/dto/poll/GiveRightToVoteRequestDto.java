package kr.ac.jejunu.polls.dto.poll;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GiveRightToVoteRequestDto {
    private String fromAddress;
    private String password;
    private String toAddress;
    private String pollAddress;
}
