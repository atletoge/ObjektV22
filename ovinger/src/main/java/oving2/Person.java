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
        setName(navn);
        setBirthday(birthday);
        setEmail(mail);
        setGender(gender);
    }

    public Person() {

    }

    public void setName(String navn) {
        if (checkNavn(navn)) {
            this.navn = navn;
        } else {
            throw new IllegalArgumentException(navn + " er ikke et gyldig navn på formatet 'Ola Nordmann'. ");
        }
    }

    private boolean checkNavn(String navn) {
        if (navn.contains(" ")) {
            String[] splittetNavn = navn.split(" ");
            if (splittetNavn.length <= 2) {
                String fornavn = splittetNavn[0];
                String etternavn = splittetNavn[1];
                if ((fornavn.matches("[a-zA-ZæøåÆØÅ]+")) && (etternavn.matches("[a-zA-ZæøåÆØÅ]+"))) {
                    if ((fornavn.length() >= 2) && etternavn.length() >= 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
        
    // atle.toge@gmail.com
    private boolean checkMail(String mail) {
        List <String> countryCodes = Arrays.asList( "ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax",
        "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs",
        "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co",
        "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh",
        "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi",
        "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu",
        "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh",
        "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu",
        "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr",
        "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np",
        "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw",
        "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk",
        "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj",
        "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va",
        "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw");
        String [] splitMail = mail.split("@");
        if (splitMail.length == 2) {
            String navn = splitMail[0];
            String domene = splitMail [1];
            String originalNavn = this.navn.replace(" ", ".");
            if (navn.toLowerCase().equals(originalNavn.toLowerCase())) {
                String[] countrycode = domene.split("\\.");
                //System.out.println(countrycode[1]);
                if ((countrycode.length == 2) &&(countryCodes.contains(countrycode[1]))) {
                    return true;
                } else {
                    throw new IllegalArgumentException(mail + " slutter ikke på formatet domene.landkode");
                }
            } else {
                throw new IllegalArgumentException(mail + " er ikke på gyldig format fornavn.etternavn før alfakrøll");
            }
            
        } else {
            throw new IllegalArgumentException(mail + " er ikke en gyldig e-postadresse");
        }
    }
        

    private boolean checkGender(char gender) {
        ArrayList<Character> genders = new ArrayList<>(Arrays.asList('M','F','\0')) ;
        if (genders.contains(gender)) {
            return true;
        } else {
            throw new IllegalArgumentException(gender+" er ikke et gyldig kjønn.");
        }
    }

    private boolean checkBirthday(Date birthday) {
        if (birthday.before(new Date())) {
            return true;
        } else {
            throw new IllegalArgumentException(birthday + " er ikke en gyldig bursdag. ");
        }
    }

    public void setBirthday(Date birthday) {
        if (checkBirthday(birthday)) {
            this.birthday = birthday;
        }
    }

    public void setEmail(String mail) {
        if (checkMail(mail)) {
            this.mail = mail;
        }
    }

    public void setGender(char gender) {
        if (checkGender(gender)) {
            this.gender = gender;
        }
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

    @Override
    public String toString() {
        return "Navn: " + navn + " Kjønn: " + gender + " E-post: "+ mail + " bursdag: " + birthday;
    }

    public static void main(String[] args) {
        Person person = new Person("Atle Toge", "atle.toge@gmail.no",new Date(3600), 'M');
        System.out.println(person);
    }
}



//Foreslå en alternativ innkapsling av navnet. Hint: del opp.

// Kunne hatt navnet i to felt
// private String firstname;
// private String lastname;


//Foreslå to alternative strategier for å kapsle inn tilstand som er koblet slik navn og e-post er. Hint: 1) samtidig og 2) dekoble.

// 1. Kunne brukt konstruktøren til å kalle på set funksjonene.
// 2.  Kunne brukt konstruktøren til å kalle på check funksjonene, så sette navn/e-post direkte i konstruktør. Ville da mistet mulighet til å endre navn i etterkant, så ikke ideelt
