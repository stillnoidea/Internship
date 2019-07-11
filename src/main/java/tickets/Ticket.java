package tickets;

import enums.Kind;
import enums.PassType;
import enums.Status;
import enums.ValidityState;
import randomizer.DataRandom;

import java.util.Date;

public class Ticket {
    private ValidityState validityState;
    private Date activationDate;
    private Date validityDate;
    private Status status;
    private ValidityPeriod validityPeriod;
    private ePassDetails passDetails;
    private DataRandom random = new DataRandom();

    public Ticket(ValidityState state, Status status) {
        this.validityState = state;
        this.status = status;
        passDetails = new ePassDetails(random.getValidityKind(), random.getValidityPassType(), random.getName(), random.getSurname(), random.getPassportNumber());
    }

    public Ticket(ValidityState state, Status status, String name, String surname, String passportNo, String passKind, String passType) {
        this.validityState = state;
        this.status = status;
        passDetails = new ePassDetails(passKind, passType, name, surname, passportNo);
    }


//    public String toString(){
//        String result = "";
//        result+=
//    }
}

class ValidityPeriod {
    Date startDate;
    Date endDate;
}


class ePassDetails {
    private Kind kind;
    private PassType type;
    private Traveler traveler;

    ePassDetails(String kind, String passType, String name, String surname, String passportNo) {
        traveler = new Traveler(name, surname, passportNo);
        this.kind = findKind(kind);
        this.type = findPassType(passType);
    }

    ePassDetails(Kind kind, PassType passType, String name, String surname, String passportNo) {
        traveler = new Traveler(name, surname, passportNo);
        this.kind = kind;
        type = passType;
    }

    private Kind findKind(String kind) {
        Kind[] list = Kind.values();
        for (int i = 0; i < list.length; i++) {
            if (list[i].getKind().equals(kind)) {
                return list[i];
            }
        }
        return null;
    }

    private PassType findPassType(String pass) {
        PassType[] list = PassType.values();
        for (int i = 0; i < list.length; i++) {
            if (list[i].getType().equals(pass)) {
                return list[i];
            }
        }
        return null;
    }
}

class Traveler {
    private String name;
    private String surname;
    private String passport;

    Traveler(String name, String surname, String passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }

    public String getSecretFullName() {
        int size = surname.length();
        StringBuilder sb = new StringBuilder();
        sb.append(name.charAt(0)).append(". ").append(surname.charAt(0));
        for (int i = 0; i < size - 2; i++) {
            sb.append("*");
        }
        sb.append(surname.charAt(size - 1));
        return sb.toString();
    }

    public String getSecretPassport() {
        StringBuilder sb = new StringBuilder();
        int size = passport.length();
        for (int i = 0; i < size - 2; i++) {
            sb.append("*");
        }
        sb.append(passport.charAt(size - 2)).append(passport.charAt(size - 1));
        return sb.toString();
    }
}