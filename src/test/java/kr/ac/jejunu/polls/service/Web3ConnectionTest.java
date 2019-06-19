package kr.ac.jejunu.polls.service;

import jnr.ffi.Struct;
import kr.ac.jejunu.polls.contract.Greeter;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;
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

        Greeter greeter1 = Greeter.load(greeter.getContractAddress(),
                web3,
                credentials, new DefaultGasProvider());
        String result = null;
        try {
            result = greeter1.greet().send();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }

    @Test
    public void SmartContract_transaction() {

        try {
            personalUnlockAccount = admin.personalUnlockAccount(
                    "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e",
                    "pass0",
                    BigInteger.valueOf(60)).send();
            String encodeConstructor = FunctionEncoder.encodeConstructor(Arrays.asList(new Uint8(3)));
            ethGetTransactionCount = web3.ethGetTransactionCount(
                    "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e",
                    DefaultBlockParameterName.LATEST
                    ).send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            Transaction transaction = Transaction.createContractTransaction(
                    "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e",
                    nonce, BigInteger.valueOf(47000000),
                    "0x608060405234801561001057600080fd5b506040516020806108b28339810180604052602081101561003057600080fd5b8101908080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060018060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001819055508060ff166002816100fa9190610101565b5050610154565b81548183558181111561012857818360005260206000209182019101610127919061012d565b5b505050565b61015191905b8082111561014d5760008082016000905550600101610133565b5090565b90565b61074f806101636000396000f3fe60806040526004361061005c576000357c0100000000000000000000000000000000000000000000000000000000900480635c19a95c14610061578063609ff1bd146100b25780639e7b8d61146100e3578063b3f98adc14610134575b600080fd5b34801561006d57600080fd5b506100b06004803603602081101561008457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610172565b005b3480156100be57600080fd5b506100c76104c7565b604051808260ff1660ff16815260200191505060405180910390f35b3480156100ef57600080fd5b506101326004803603602081101561010657600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610543565b005b34801561014057600080fd5b506101706004803603602081101561015757600080fd5b81019080803560ff169060200190929190505050610640565b005b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16156101d257506104c4565b5b600073ffffffffffffffffffffffffffffffffffffffff16600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160029054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415801561030057503373ffffffffffffffffffffffffffffffffffffffff16600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160029054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614155b1561036f57600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160029054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691506101d3565b3373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156103a957506104c4565b60018160010160006101000a81548160ff021916908315150217905550818160010160026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16156104aa57816000015460028260010160019054906101000a900460ff1660ff1681548110151561048b57fe5b90600052602060002001600001600082825401925050819055506104c1565b816000015481600001600082825401925050819055505b50505b50565b6000806000905060008090505b6002805490508160ff16101561053e578160028260ff168154811015156104f757fe5b906000526020600020016000015411156105315760028160ff1681548110151561051d57fe5b906000526020600020016000015491508092505b80806001019150506104d4565b505090565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415806105eb5750600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160009054906101000a900460ff165b156105f55761063d565b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001819055505b50565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16806106a857506002805490508260ff1610155b156106b35750610720565b60018160010160006101000a81548160ff021916908315150217905550818160010160016101000a81548160ff021916908360ff160217905550806000015460028360ff1681548110151561070457fe5b9060005260206000200160000160008282540192505081905550505b5056fea165627a7a723058209061ffc04667804683fe01748db07db99f66b416464677c76a87e047d3ff2a430029" + encodeConstructor
            );

            transactionResponse = web3.ethSendTransaction(transaction).send();
            String transactionHash = transactionResponse.getTransactionHash();
            System.out.println(transactionHash);
            transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();

            while (transactionReceipt.getTransactionReceipt().isEmpty()) {
                try {
                    transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        String contractAddress = transactionReceipt.getTransactionReceipt().get().getContractAddress();
        System.out.println(contractAddress);
    }

    @Test
    public void CreateAccount() {
        try {
            String filename = WalletUtils.generateFullNewWalletFile(
                    "pass26", new File("/home/plenilune/data_testnet/keystore"));
            System.out.println(filename);
        } catch (CipherException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Contract_call() {
        EthCall response = null;

        Function function = new Function(
                "greet",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Utf8String>() {
                }));

        String encodedFunction = FunctionEncoder.encode(function);
        try {
            response = web3.ethCall(
                    Transaction.createEthCallTransaction(
                            "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e",
                            "0x139040faf886ce386349446e004f9447b3ef8f58",
                            encodedFunction),
                    DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        System.out.println(someTypes.get(0));

    }

    @Test
    public void Contract_transaction() {

        try {
            ethGetTransactionCount = web3.ethGetTransactionCount(
                    "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e", DefaultBlockParameterName.LATEST
            ).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        Function function = new Function(
                "giveRightToVote",
                Arrays.asList(new Address("0xbbf516b609c9b0d6474c77b8758bd93a1e15eb76")),
                Collections.<TypeReference<?>>emptyList());

        String encodedFunction = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createFunctionCallTransaction(
                "0xcfd8cbe5da3002b52c650ce1302e10c6d1be644e",
                nonce,
                BigInteger.valueOf(43382), BigInteger.valueOf(3000000),
                "0x86afaa19c923d2fb1dcfe24ec03d28be3843afe3",
                encodedFunction);

        try {
            transactionResponse = web3.ethSendTransaction(transaction).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String transactionHash = transactionResponse.getTransactionHash();
        System.out.println(transactionHash);


    }

    @Test
    public void vote() throws IOException, ExecutionException, InterruptedException {

        personalUnlockAccount = admin.personalUnlockAccount(
                "0xbbf516b609c9b0d6474c77b8758bd93a1e15eb76",
                "pass2",
                BigInteger.valueOf(60)).send();

        ethGetTransactionCount = web3.ethGetTransactionCount(
                "0xbbf516b609c9b0d6474c77b8758bd93a1e15eb76",
                DefaultBlockParameterName.LATEST
        ).send();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        Function function = new Function(
                "vote",
                Arrays.asList(new Uint8(2)),
                Collections.<TypeReference<?>>emptyList());

        String encodedFunction = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createFunctionCallTransaction(
                "0xbbf516b609c9b0d6474c77b8758bd93a1e15eb76",
                nonce,
                BigInteger.valueOf(43382), BigInteger.valueOf(3000000),
                "0x86afaa19c923d2fb1dcfe24ec03d28be3843afe3",
                encodedFunction);

        transactionResponse = web3.ethSendTransaction(transaction).sendAsync().get();

        String transactionHash = transactionResponse.getTransactionHash();
        System.out.println(transactionHash);

    }

    @Test
    public void winningProposal() {

        Function function = new Function(
                "winningProposal",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint8>() {
                }));

        String encodedFunction = FunctionEncoder.encode(function);

        EthCall response = null;

        try {
            response = web3.ethCall(
                    Transaction.createEthCallTransaction(
                            "0xbbf516b609c9b0d6474c77b8758bd93a1e15eb76",
                            "0x86afaa19c923d2fb1dcfe24ec03d28be3843afe3",
                            encodedFunction),
                    DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        System.out.println(someTypes.get(0).getValue());

    }


}
