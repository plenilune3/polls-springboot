package kr.ac.jejunu.polls.service;

import kr.ac.jejunu.polls.domain.AccountManagement;
import kr.ac.jejunu.polls.domain.ContractManagement;
import kr.ac.jejunu.polls.dto.contract.ContractCreateRequestDto;
import kr.ac.jejunu.polls.dto.contract.CreateAccountRequestDto;
import kr.ac.jejunu.polls.dto.contract.UnlockAccountRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    private AccountManagement accountManagement;
    private ContractManagement contractManagement;

    public PollService() {
        contractManagement = new ContractManagement();
        accountManagement = new AccountManagement();
    }

    public boolean unlockAccount(UnlockAccountRequestDto dto) {
        return accountManagement.unlockAccount(dto);
    }

    public String createAccount(CreateAccountRequestDto dto) {
        return accountManagement.createAccount(dto);
    }

    public String createContract(ContractCreateRequestDto dto) {
        return contractManagement.createContract(dto);
    }

}
