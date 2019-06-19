package kr.ac.jejunu.polls.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class Ballot extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060405160208061046d8339810180604052602081101561003057600080fd5b5051600080546001600160a01b03191633178082556001600160a01b031681526001602081905260409091205560ff811661006c600282610073565b50506100bd565b8154818355818111156100975760008381526020902061009791810190830161009c565b505050565b6100ba91905b808211156100b657600081556001016100a2565b5090565b90565b6103a1806100cc6000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80635c19a95c14610051578063609ff1bd146100795780639e7b8d6114610097578063b3f98adc146100bd575b600080fd5b6100776004803603602081101561006757600080fd5b50356001600160a01b03166100dd565b005b610081610230565b6040805160ff9092168252519081900360200190f35b610077600480360360208110156100ad57600080fd5b50356001600160a01b0316610298565b610077600480360360208110156100d357600080fd5b503560ff166102f5565b3360009081526001602081905260409091209081015460ff1615610101575061022d565b5b6001600160a01b0382811660009081526001602081905260409091200154620100009004161580159061015957506001600160a01b0382811660009081526001602081905260409091200154620100009004163314155b1561018b576001600160a01b039182166000908152600160208190526040909120015462010000900490911690610102565b6001600160a01b0382163314156101a2575061022d565b6001818101805460ff1916821762010000600160b01b031916620100006001600160a01b0386169081029190911790915560009081526020829052604090209081015460ff16156102225781546001820154600280549091610100900460ff1690811061020b57fe5b60009182526020909120018054909101905561022a565b815481540181555b50505b50565b600080805b60025460ff82161015610293578160028260ff168154811061025357fe5b9060005260206000200160000154111561028b5760028160ff168154811061027757fe5b906000526020600020016000015491508092505b600101610235565b505090565b6000546001600160a01b0316331415806102ce57506001600160a01b0381166000908152600160208190526040909120015460ff165b156102d85761022d565b6001600160a01b0316600090815260016020819052604090912055565b3360009081526001602081905260409091209081015460ff168061031e575060025460ff831610155b15610329575061022d565b6001818101805460ff191690911761ff00191661010060ff85169081029190911790915581546002805491929091811061035f57fe5b600091825260209091200180549091019055505056fea165627a7a72305820d93c52cd839023038d7ff12025e688f325ebeb3027ac2a4d4557b4b2e9c1cf890029";

    public static final String FUNC_DELEGATE = "delegate";

    public static final String FUNC_WINNINGPROPOSAL = "winningProposal";

    public static final String FUNC_GIVERIGHTTOVOTE = "giveRightToVote";

    public static final String FUNC_VOTE = "vote";

    @Deprecated
    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> delegate(String to) {
        final Function function = new Function(
                FUNC_DELEGATE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> winningProposal() {
        final Function function = new Function(FUNC_WINNINGPROPOSAL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> giveRightToVote(String toVoter) {
        final Function function = new Function(
                FUNC_GIVERIGHTTOVOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(toVoter)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> vote(BigInteger toProposal) {
        final Function function = new Function(
                FUNC_VOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(toProposal)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Ballot(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Ballot(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_numProposals)));
        return deployRemoteCall(Ballot.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_numProposals)));
        return deployRemoteCall(Ballot.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_numProposals)));
        return deployRemoteCall(Ballot.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_numProposals)));
        return deployRemoteCall(Ballot.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
