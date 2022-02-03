package oving2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.List;

public class Person {
    
    private String navn;
    private String mail;
    private Date birthday;
    private char gender;


    public Person(String navn, String mail, Date birthday, char gender) {
        this.checkNavn(navn);
        this.checkMail(mail);
        this.checkBirthday(birthday);
        this.checkGender(gender);
        this.navn = navn;
        this.mail = mail;
        this.birthday = birthday;
        this.gender = gender;
    }

    public void setName(String navn) {
        this.checkNavn(navn);
        this.navn = navn;
    }

    private void checkNavn(String navn) {
        if (!(Pattern.matches("^[A-Å][a-å]+\s[A-Å][a-å]+$", navn))) {
            throw new IllegalArgumentException(navn+" er ikke et navn på gyldig format. Eks: Ola Nordmann");
        }

    }

    private void checkMail(String mail) {
        String[] landkode = {"ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw"};
        if (!(mail == null)) {
            if (Pattern.matches("^[a-å]+\\.[a-å]+@[a-å]+\\.[a-z]{2}$", mail)) {
                String nyStreng = mail.substring(mail.length() - 2);
                if (!(landkode.contains(nyStreng))) {
                    throw new IllegalArgumentException(mail+" er ikke en gyldig e-postadresse.");
                }
            }
        }
        else {
            throw new IllegalArgumentException(mail+" er ikke en gyldig e-postadresse.");
        }

    }

    private void checkGender(gender) {
        char[] bokstaver = {"M", "F", "\0"};
        if (!(bokstaver.contains(gender))) {
            throw new IllegalArgumentException(gender+" er ikke et gyldig kjønn.");
        }
    }

    public void setBirthday(Date birthday) {
        this.checkBirthday(birthday);
        this.birthday = birthday;
    }

    public void setEmail(String mail) {
        this.checkMail(mail);
        this.mail = mail;
    }

    public void setGender(char gender) {
        this.checkGender(gender);
        this.gender = gender;
    }

    public String getName() {
        return navn;
    }

    public String getEmail() {
        return mail;
    }

    public Date getBirthday() {
        return birthday;
    }

    public char getGender() {
        return gender;
    }


    public static void main(String[] args) {
        Person person = new Person("Atle Toge", "atle.toge@gmail.com", , 'M') 
    }
}
