package kr.ac.jejunu.polls.dto.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnlockAccountRequestDto {
    private String address;
    private String password;
}
