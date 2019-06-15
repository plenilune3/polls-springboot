package kr.ac.jejunu.polls.service;

import jnr.ffi.Struct;
import kr.ac.jejunu.polls.contract.Greeter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Web3ConnectionTest {
    private Web3j web3 = Web3j.build(new HttpService());
    private Web3ClientVersion web3ClientVersion;
    private Admin admin = Admin.build(new HttpService());
    private PersonalUnlockAccount personalUnlockAccount;
    private EthSendTransaction transactionResponse;
    private Greeter greeter;
    private Credentials credentials;
    private EthGetTransactionCount ethGetTransactionCount;
    private EthGetTransactionReceipt transactionReceipt;

    @Test
    public void Connection() {

        try {
//            web3ClientVersion = web3.web3ClientVersion().send();
            web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println("Client Version : " + clientVersion);
    }

    @Test
    public void UnlockAccount() {

        try {
            personalUnlockAccount = admin.personalUnlockAccount(
                    "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e",
                    "pass0",
                    BigInteger.valueOf(60)).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String accountUnlocked = personalUnlockAccount.accountUnlocked().toString();
        System.out.println("Account Unlocked : " + accountUnlocked);

    }

    @Test
    public void SmartContract_credential(){

        try {
            credentials = WalletUtils.loadCredentials("pass0",
                "/home/plenilune/data_testnet/keystore/UTC--2019-05-01T17-20-32.601445167Z--cfd8cbe5da3002b52c650ce1302e10c6d1be644e");
            greeter = Greeter.deploy(
                    web3, credentials, new DefaultGasProvider(), "Hi"
            ).send();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(greeter.getContractAddress());
    }

    @Test
    public void SmartContract_transaction() {
        String transactionHash= null;

        try {
            personalUnlockAccount = admin.personalUnlockAccount(
                    "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e",
                    "pass0",
                    BigInteger.valueOf(60)).send();
            String encodeConstructor = FunctionEncoder.encodeConstructor(Arrays.asList(new org.web3j.abi.datatypes.Utf8String("hi")));
            ethGetTransactionCount = web3.ethGetTransactionCount(
                    "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e", DefaultBlockParameterName.LATEST
                    ).send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            Transaction transaction = Transaction.createContractTransaction(
                    "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e",
                    nonce, BigInteger.valueOf(47000000),
                    "0x608060405234801561001057600080fd5b506040516103c73803806103c78339810180604052602081101561003357600080fd5b81019080805164010000000081111561004b57600080fd5b8281019050602081018481111561006157600080fd5b815185600182028301116401000000008211171561007e57600080fd5b5050929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600190805190602001906100dc9291906100e3565b5050610188565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061012457805160ff1916838001178555610152565b82800160010185558215610152579182015b82811115610151578251825591602001919060010190610136565b5b50905061015f9190610163565b5090565b61018591905b80821115610181576000816000905550600101610169565b5090565b90565b610230806101976000396000f3fe608060405260043610610046576000357c01000000000000000000000000000000000000000000000000000000009004806341c0e1b51461004b578063cfae321714610062575b600080fd5b34801561005757600080fd5b506100606100f2565b005b34801561006e57600080fd5b50610077610162565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100b757808201518184015260208101905061009c565b50505050905090810190601f1680156100e45780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610160573373ffffffffffffffffffffffffffffffffffffffff16ff5b565b606060018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156101fa5780601f106101cf576101008083540402835291602001916101fa565b820191906000526020600020905b8154815290600101906020018083116101dd57829003601f168201915b505050505090509056fea165627a7a72305820738577ed3d372ca0aed4c028bdd42af7113dc805d80bad5bc1d3d20580b854090029" + encodeConstructor
            );

            transactionResponse = web3.ethSendTransaction(transaction).send();
            transactionHash = transactionResponse.getTransactionHash();
            System.out.println(transactionHash);

            transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!(transactionReceipt.getTransactionReceipt().isPresent())) {
            try {
                transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String contractAddress = transactionReceipt.getTransactionReceipt().get().getContractAddress();
        System.out.println(contractAddress);
    }


}
