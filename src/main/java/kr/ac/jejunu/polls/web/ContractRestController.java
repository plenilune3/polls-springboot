package kr.ac.jejunu.polls.web;

import kr.ac.jejunu.polls.dto.contract.ContractCreateRequestDto;
import kr.ac.jejunu.polls.dto.contract.CreateAccountRequestDto;
import kr.ac.jejunu.polls.dto.contract.UnlockAccountRequestDto;
import kr.ac.jejunu.polls.service.PollService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/polls")
public class ContractRestController {

    private PollService pollService;

    @PostMapping(value = "/unlockaccount")
    public boolean unlockAccount(@RequestBody UnlockAccountRequestDto dto) {
        return pollService.unlockAccount(dto);
    }

    @PostMapping(value = "/createaccount")
    public String createAccount(@RequestBody CreateAccountRequestDto dto) {
        return pollService.createAccount(dto);
    }

    @PostMapping(value = "/createcontract")
    public String createContract(@RequestBody ContractCreateRequestDto dto) {
        return pollService.createContract(dto);
    }
}

