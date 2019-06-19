package kr.ac.jejunu.polls.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jejunu.polls.dto.poll.CreateAccountRequestDto;
import kr.ac.jejunu.polls.dto.poll.UnlockAccountRequestDto;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AccountManagement {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private Web3j web3j = Web3j.build(new HttpService());
    private Admin admin = Admin.build(new HttpService());
    private PersonalUnlockAccount personalUnlockAccount;

    public Boolean unlockAccount(UnlockAccountRequestDto dto) {
        try {
            personalUnlockAccount = admin.personalUnlockAccount(
                    dto.getAddress(), dto.getPassword(),
                    BigInteger.valueOf(60)).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (personalUnlockAccount.accountUnlocked() != null) {
            return personalUnlockAccount.accountUnlocked();
        } else {
            return false;
        }
    }

    public String createAccount(CreateAccountRequestDto dto) {

        String address = "0x";

        try {
            address += generateNewWalletFile(
                    dto.getPassword(),
                    new File("/home/plenilune/data_testnet/keystore"),
                    true).getAddress();
            System.out.println(address);

        } catch (CipherException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
            e.printStackTrace();
        }

        return address;
    }

    public static WalletFile generateNewWalletFile(
            String password, File destinationDirectory, boolean useFullScrypt)
            throws CipherException, IOException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException {

        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        return generateWalletFile(password, ecKeyPair, destinationDirectory, useFullScrypt);
    }

    public static WalletFile generateWalletFile(
            String password, ECKeyPair ecKeyPair, File destinationDirectory, boolean useFullScrypt)
            throws CipherException, IOException {

        WalletFile walletFile;
        if (useFullScrypt) {
            walletFile = Wallet.createStandard(password, ecKeyPair);
        } else {
            walletFile = Wallet.createLight(password, ecKeyPair);
        }

        String fileName = getWalletFileName(walletFile);
        File destination = new File(destinationDirectory, fileName);

        objectMapper.writeValue(destination, walletFile);

        return walletFile;
    }

    private static String getWalletFileName(WalletFile walletFile) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(
                "'UTC--'yyyy-MM-dd'T'HH-mm-ss.nVV'--'");
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

        return now.format(format) + walletFile.getAddress() + ".json";
    }

}
