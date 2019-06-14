package kr.ac.jejunu.polls.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Web3ConnectionTest {
    Web3j web3 = Web3j.build(new HttpService());
    Web3ClientVersion web3ClientVersion;

    @Test
    public void send() {
        try {
//            web3ClientVersion = web3.web3ClientVersion().send();
            web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println("Client Version : " + clientVersion);
    }

}
