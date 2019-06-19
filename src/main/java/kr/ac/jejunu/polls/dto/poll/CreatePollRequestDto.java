package kr.ac.jejunu.polls.dto.poll;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePollRequestDto {
    private String fromAddress;
    private String password;
    private int numProposals;
}
