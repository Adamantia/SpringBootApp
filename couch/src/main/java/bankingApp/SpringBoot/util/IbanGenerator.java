package bankingApp.SpringBoot.util;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

/** IBAN rules: https://nl.wikipedia.org/wiki/International_Bank_Account_Number
 *
 * e.g.  NL10 CCH0 1234 567 90
 */

@Service
public class IbanGenerator {


    private long generatePrefixAccount() {
        //generate first part of the account 0
        final long MAX = 999999999L; // 999999999
        final long MIN = 0L;
        return (long) ((MAX - MIN + 1) * Math.random()) + MIN;
    }

    private int generateCheckDigits(long account) {
        account *= 1000000L;
        account += 232100L;
        BigInteger bigaccount;                              //convert to BigInteger because long is too short
        bigaccount = BigInteger.valueOf(account);
        bigaccount = bigaccount.add(new BigInteger("122430120000000000000000"));
        return (new BigInteger("98").subtract(bigaccount.mod(new BigInteger("97")))).intValue();        //calculate & return check digits
    }

    private String generateAccountAs10digitString(long account){
        String accAsString = Long.toString(account);
        StringBuilder tenDigitAccount = new StringBuilder(accAsString);
        for (int i = 0; i < 10-accAsString.length(); i++){
            tenDigitAccount.insert(0, 0);                   //add zero's in front of account number until it's a 10 digit number
        }
        return tenDigitAccount.toString();
    }


    public String generateIban() {
        long account = generatePrefixAccount();                                 //when database is up, check if IBAN already
        int checkDigits = generateCheckDigits(account);
        StringBuilder iban = new StringBuilder();
        iban.append("NL");   //add 'NL' numerical and 00 to end of acc nr according to IBAN rules
        if (checkDigits < 10) {                                              //if checkdigits < 10, add a 0 in front
            String checkDigitsString = Integer.toString(checkDigits);
            checkDigitsString = "0" + checkDigitsString;
            iban.append(checkDigitsString);
        } else {
            iban.append(checkDigits);
        }
        iban.append("CCH");
        iban.append(generateAccountAs10digitString(account));
        return iban.toString();
    }
}
