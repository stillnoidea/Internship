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

    
}

class ValidityPeriod{
    Date startDate;
    Date endDate;
}


class ePassDetails {
    private Kind kind;
    private PassType type;
    private Traveler traveler;
}

class Traveler{
    private String name;
    private String surname;
    private String passport;
}