package kr.ac.jejunu.polls.dto.poll;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WinnerRequestDto {
    private String fromAddress;
    private String password;
    private String pollAddress;
}
