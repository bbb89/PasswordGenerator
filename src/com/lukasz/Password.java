package com.lukasz;

import java.util.*;

/**
 * Created by Łukasz on 09.05.2017.
 */

public class Password {
    private int charsNum;
    private int specialCharsNum;
    private int numbersNum;
    private int upperLower;
    private int lettersNum;
    List<Character> chars;
    private String password = "";

    public Password(int charsNum, int specialCharsNum, int numbersNum, int upperLower) {
        this.charsNum = charsNum;
        this.specialCharsNum = specialCharsNum;
        this.numbersNum = numbersNum;
        this.lettersNum = charsNum - specialCharsNum - numbersNum;

        if(lettersNum <= 0) this.upperLower = 0;
        else this.upperLower = upperLower;

        chars = new ArrayList<>();

        password = generatePassword();
    }

    private String generatePassword() {
        Random random = new Random();

        for(int i = 0; i < specialCharsNum; i++) {
            Character ch = (char) ('!' + random.nextInt(14));
            chars.add(ch);
        }
        for(int i = 0; i < numbersNum; i++) {
            Character ch = (char) ('0' + random.nextInt(9));
            chars.add(ch);
        }

        switch (this.upperLower) {
            case 0:
                break;
            case 1:
                for(int i = 0; i < lettersNum; i++) {
                    Character ch = (char) ('a' + random.nextInt(25));
                    chars.add(ch);
                }
                break;
            case 2:
                for(int i = 0; i < lettersNum; i++) {
                    Character ch = (char) ('A' + random.nextInt(25));
                    chars.add(ch);
                }
                break;
            case 3:
                int lowerNum = random.nextInt(lettersNum);
                for(int i = 0; i < lowerNum; i++) {
                    Character ch = (char) ('a' + random.nextInt(25));
                    chars.add(ch);
                }
                for(int i = 0; i < lettersNum - lowerNum; i++) {
                    Character ch = (char) ('A' + random.nextInt(25));
                    chars.add(ch);
                }
                break;
        }

        Collections.shuffle(chars);
        ListIterator<Character> iterator = chars.listIterator();
        while (iterator.hasNext()) {
            password += iterator.next();
        }

        return password;
    }

    public String getPassword() {
        return password;
    }
}
