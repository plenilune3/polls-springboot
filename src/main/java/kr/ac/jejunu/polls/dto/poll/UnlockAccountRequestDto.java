package kr.ac.jejunu.polls.dto.poll;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnlockAccountRequestDto {
    private String address;
    private String password;

    @Builder
    public UnlockAccountRequestDto(String address, String password) {
        this.address = address;
        this.password = password;
    }
}
