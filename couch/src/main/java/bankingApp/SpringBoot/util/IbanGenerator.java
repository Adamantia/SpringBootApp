package bankingApp.SpringBoot.util;

import bankingApp.SpringBoot.model.BankAccount;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * IBAN rules: https://nl.wikipedia.org/wiki/International_Bank_Account_Number
 * <p>
 * e.g.  NL10 CCH0 1234 567 90
 */

@Service
public class IbanGenerator {

    private final static String COUNTRYCODE = "NL";
    private final static String BANKCODE = "CCHB";
    private String accountNr;
    private final static int ACCOUNTNR_LENGTH = 7;

    public String generateIban() {
        StringBuilder iban = new StringBuilder();
        // run method agan to find nl = 2321;
        String controlcode = getControlCode();
        iban.append(COUNTRYCODE);
        iban.append(controlcode);
        iban.append(BANKCODE);
        iban.append("000");
        iban.append(accountNr);
        System.out.println("iban is "+ iban.toString());
        return iban.toString();
    }




    public String generateAccountNr() {
        Random random = new Random();
        StringBuilder sb1 = new StringBuilder();
        random.ints(ACCOUNTNR_LENGTH, 0, 9)
                .forEach(x -> sb1.append(String.valueOf(x)));
        return sb1.toString();
    }


    public String getControlCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNrFromCode(BANKCODE));
        addZerosToAccount(sb, 3);
        accountNr = generateAccountNr();
        sb.append(accountNr);
        sb.append(getNrFromCode(COUNTRYCODE));
        addZerosToAccount(sb, 2);
        BigInteger b1 = new BigInteger(sb.toString());
        BigInteger b2 = new BigInteger("97");
        BigInteger b3 = new BigInteger("98");
        BigInteger b4 = b3.subtract(b1.mod(b2));
        System.out.println("control code is " + b4.toString());
        return b4.toString();
    }


    public String getNrFromCode(String code) {
        Map<Character, Integer> hmap = generateAlphabetToNrMap();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < code.length(); j++) {
            final char c = code.charAt(j);
            hmap.forEach((key, value) -> {
                if (c == key) {
                    sb.append(value.toString());
                }
            });
        }
        return sb.toString();
    }


    public Map<Character, Integer> generateAlphabetToNrMap() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Map<Character, Integer> hmap = new HashMap<Character, Integer>();
        int i = 10;
        for (int j = 0; j < alphabet.length(); j++) {
            hmap.put(alphabet.charAt(j), i);
            i++;
        }
        return hmap;
    }

    public void addZerosToAccount(StringBuilder sb, int zeros) {
        for (int i = 0; i < zeros; i++) {
            sb.append("0");
        }
    }
}
