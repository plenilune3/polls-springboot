package kr.ac.jejunu.polls.domain;

import kr.ac.jejunu.polls.dto.poll.CreatePollRequestDto;
import kr.ac.jejunu.polls.dto.poll.GiveRightToVoteRequestDto;
import kr.ac.jejunu.polls.dto.poll.VoteRequestDto;
import kr.ac.jejunu.polls.dto.poll.WinnerRequestDto;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PollManagement {
    private Web3j web3 = Web3j.build(new HttpService());
    private EthSendTransaction transactionResponse;
    private EthGetTransactionCount ethGetTransactionCount;
    private EthGetTransactionReceipt transactionReceipt;
    private EthCall response;

    public String createPoll(CreatePollRequestDto dto) {

        try {

            ethGetTransactionCount = web3.ethGetTransactionCount(
                    dto.getFromAddress(),
                    DefaultBlockParameterName.LATEST
            ).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        String encodeConstructor = FunctionEncoder
                .encodeConstructor(Arrays.asList(new Uint8(dto.getNumProposals())));

        Transaction transaction = Transaction.createContractTransaction(
                dto.getFromAddress(),
                nonce, BigInteger.valueOf(47000000),
                "0x608060405234801561001057600080fd5b506040516020806108b28339810180604052602081101561003057600080fd5b8101908080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060018060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001819055508060ff166002816100fa9190610101565b5050610154565b81548183558181111561012857818360005260206000209182019101610127919061012d565b5b505050565b61015191905b8082111561014d5760008082016000905550600101610133565b5090565b90565b61074f806101636000396000f3fe60806040526004361061005c576000357c0100000000000000000000000000000000000000000000000000000000900480635c19a95c14610061578063609ff1bd146100b25780639e7b8d61146100e3578063b3f98adc14610134575b600080fd5b34801561006d57600080fd5b506100b06004803603602081101561008457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610172565b005b3480156100be57600080fd5b506100c76104c7565b604051808260ff1660ff16815260200191505060405180910390f35b3480156100ef57600080fd5b506101326004803603602081101561010657600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610543565b005b34801561014057600080fd5b506101706004803603602081101561015757600080fd5b81019080803560ff169060200190929190505050610640565b005b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16156101d257506104c4565b5b600073ffffffffffffffffffffffffffffffffffffffff16600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160029054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415801561030057503373ffffffffffffffffffffffffffffffffffffffff16600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160029054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614155b1561036f57600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160029054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691506101d3565b3373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156103a957506104c4565b60018160010160006101000a81548160ff021916908315150217905550818160010160026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16156104aa57816000015460028260010160019054906101000a900460ff1660ff1681548110151561048b57fe5b90600052602060002001600001600082825401925050819055506104c1565b816000015481600001600082825401925050819055505b50505b50565b6000806000905060008090505b6002805490508160ff16101561053e578160028260ff168154811015156104f757fe5b906000526020600020016000015411156105315760028160ff1681548110151561051d57fe5b906000526020600020016000015491508092505b80806001019150506104d4565b505090565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415806105eb5750600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160009054906101000a900460ff165b156105f55761063d565b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001819055505b50565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16806106a857506002805490508260ff1610155b156106b35750610720565b60018160010160006101000a81548160ff021916908315150217905550818160010160016101000a81548160ff021916908360ff160217905550806000015460028360ff1681548110151561070457fe5b9060005260206000200160000160008282540192505081905550505b5056fea165627a7a723058209061ffc04667804683fe01748db07db99f66b416464677c76a87e047d3ff2a430029" + encodeConstructor
        );

        getResponse(transaction);

        System.out.println(transactionReceipt.getTransactionReceipt().get().getContractAddress());
        return transactionReceipt.getTransactionReceipt().get().getContractAddress();

    }


    public String giveRightToVote(GiveRightToVoteRequestDto dto) {

        try {
            ethGetTransactionCount = web3.ethGetTransactionCount(
                    dto.getFromAddress(), DefaultBlockParameterName.LATEST
            ).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        Function function = new Function(
                "giveRightToVote",
                Arrays.asList(new Address(dto.getToAddress())),
                Collections.<TypeReference<?>>emptyList());

        String encodedFunction = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createFunctionCallTransaction(
                dto.getFromAddress(),
                nonce,
                BigInteger.valueOf(43382), BigInteger.valueOf(3000000),
                dto.getPollAddress(),
                encodedFunction);

        getResponse(transaction);

        return transactionReceipt.getTransactionReceipt().get().getTransactionHash();
    }

    public String vote(VoteRequestDto dto) {

        try {
            ethGetTransactionCount = web3.ethGetTransactionCount(
                    dto.getFromAddress(),
                    DefaultBlockParameterName.LATEST).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        Function function = new Function(
                "vote",
                Arrays.asList(new Uint8(dto.getVote())),
                Collections.<TypeReference<?>>emptyList());

        String encodedFunction = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createFunctionCallTransaction(
                dto.getFromAddress(),
                nonce,
                BigInteger.valueOf(43382), BigInteger.valueOf(3000000),
                dto.getPollAddress(),
                encodedFunction);

        getResponse(transaction);

        return transactionReceipt.getTransactionReceipt().get().getTransactionHash();
    }

    public String winnerProposal(WinnerRequestDto dto) {

        Function function = new Function(
                "winningProposal",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint8>() {
                }));

        String encodedFunction = FunctionEncoder.encode(function);

        try {
            response = web3.ethCall(
                    Transaction.createEthCallTransaction(
                            dto.getFromAddress(),
                            dto.getPollAddress(),
                            encodedFunction),
                    DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        System.out.println(someTypes.get(0).getValue());

        return someTypes.get(0).getValue().toString();

    }

    private void getResponse(Transaction transaction) {

        try {
            transactionResponse = web3.ethSendTransaction(transaction).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String transactionHash = transactionResponse.getTransactionHash();
        System.out.println(transactionHash);

        try {
            transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (transactionReceipt.getTransactionReceipt().isEmpty()) {
            try {
                transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
