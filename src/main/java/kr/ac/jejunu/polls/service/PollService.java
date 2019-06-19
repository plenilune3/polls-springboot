package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.AccountManagement;
import kr.ac.jejunu.polls.domain.PollManagement;
import kr.ac.jejunu.polls.dto.poll.*;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    private AccountManagement accountManagement;
    private PollManagement pollManagement;

    public PollService() {
        pollManagement = new PollManagement();
        accountManagement = new AccountManagement();
    }

    public boolean unlockAccount(UnlockAccountRequestDto dto) {
        return accountManagement.unlockAccount(dto);
    }

    public String createAccount(CreateAccountRequestDto dto) {

        return accountManagement.createAccount(dto);
    }

    public String createPoll(CreatePollRequestDto dto) {

        UnlockAccountRequestDto unlockAccountRequestDto = getUnlockAccountRequestDto(dto.getFromAddress(), dto.getPassword());

        if (accountManagement.unlockAccount(unlockAccountRequestDto))
            return pollManagement.createPoll(dto);
        else
            return "비밀번호 확인";
    }

    public String giveRightToVote(GiveRightToVoteRequestDto dto) {

        UnlockAccountRequestDto unlockAccountRequestDto = getUnlockAccountRequestDto(dto.getFromAddress(), dto.getPassword());

        if (accountManagement.unlockAccount(unlockAccountRequestDto))
            return pollManagement.giveRightToVote(dto);
        else
            return "비밀번호 확인";
    }

    public String vote(VoteRequestDto dto) {

        UnlockAccountRequestDto unlockAccountRequestDto = getUnlockAccountRequestDto(dto.getFromAddress(), dto.getPassword());

        if (accountManagement.unlockAccount(unlockAccountRequestDto))
            return pollManagement.vote(dto);
        else
            return "비밀번호 확인";

    }

    public String winningProposal(WinnerRequestDto dto) {

        UnlockAccountRequestDto unlockAccountRequestDto = getUnlockAccountRequestDto(dto.getFromAddress(), dto.getPassword());

        if (accountManagement.unlockAccount(unlockAccountRequestDto))
            return pollManagement.winnerProposal(dto);
        else
            return "비밀번호 확인";
    }

    private UnlockAccountRequestDto getUnlockAccountRequestDto(String fromAddress, String password) {
        return UnlockAccountRequestDto.builder()
                .address(fromAddress)
                .password(password)
                .build();
    }
}
