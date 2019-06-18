package kr.ac.jejunu.polls.dto.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateAccountRequestDto {
    private String password;
}
