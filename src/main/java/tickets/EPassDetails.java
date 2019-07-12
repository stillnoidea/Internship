package tickets;

import enums.Status;

class EPassDetails {
    private String kind;
    private String type;
    private Traveler traveler;
    private Status status;
    private ValidityPeriod validityPeriod;

    EPassDetails(String kind, String passType, String name, String surname, String passportNo, Status status, ValidityPeriod validityPeriod) {
        traveler = new Traveler(name, surname, passportNo);
        this.kind = kind;
        this.type = passType;
        this.status = status;
        this.validityPeriod = validityPeriod;
    }

    public String getKind() {
        return kind;
    }

    public String getType() {
        return type;
    }

    public Traveler getTraveler() {
        return traveler;
    }

}