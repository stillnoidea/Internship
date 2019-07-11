package tickets;

import enums.Kind;
import enums.PassType;
import enums.Status;
import enums.ValidityState;

import java.util.Date;

public class Ticket {
    private ValidityState validityState;
    private Date activationDate;
    private Date validityDate;
    private Status status;
    private ValidityPeriod validityPeriod;
    private ePassDetails passDetails;

    public Ticket(ValidityState state, Status status) {
        this.validityState = state;
        this.status = status;
    }

    public Ticket(ValidityState state, Status status, String name, String surname, String passportNo, String passKind, String passType) {
        this.validityState = state;
        this.status = status;
        passDetails = new ePassDetails(passKind, passType, name, surname, passportNo);
    }


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
    }

//    private Kind findKind(String kind) {
//        Kind[] list = Kind.values();
//        for (int i = 0; i < list.length; i++) {
//            if (list[i].getKind().equals(kind)) {
//                return list[i];
//            }
//        }
//
//    }
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
}