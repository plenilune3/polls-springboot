package kr.ac.jejunu.polls.web;

import kr.ac.jejunu.polls.dto.poll.*;
import kr.ac.jejunu.polls.service.PollService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/polls")
public class PollRestController {

    private PollService pollService;

    @PostMapping(value = "/unlockaccount")
    public boolean unlockAccount(@RequestBody UnlockAccountRequestDto dto) {
        return pollService.unlockAccount(dto);
    }

    @PostMapping(value = "/createaccount")
    public String createAccount(@RequestBody CreateAccountRequestDto dto) {
        return pollService.createAccount(dto);
    }

    @PostMapping(value = "/createpoll")
    public String createPoll(@RequestBody CreatePollRequestDto dto) {
        return pollService.createPoll(dto);
    }

    @PostMapping(value = "/giverighttovote")
    public String giveRightToVote(@RequestBody GiveRightToVoteRequestDto dto) {
        return pollService.giveRightToVote(dto);
    }

    @PostMapping(value = "/vote")
    public String vote(@RequestBody VoteRequestDto dto) {
        return pollService.vote(dto);
    }

    @PostMapping(value = "/winningproposal")
    public String winnerProposal(@RequestBody WinnerRequestDto dto) {
        return pollService.winningProposal(dto);
    }
}

