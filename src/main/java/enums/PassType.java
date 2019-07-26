package enums;

public enum PassType {
    FST_SENIOR("1st class Senior"),
    FST_ADULT("1st class Adult"),
    FST_YOUTH("1st class Youth"),
    SC_SENIOR("2nd class Senior"),
    SC_ADULT("2nd class Adult"),
    SC_YOUTH("2nd class Youth");

    private String type;

    PassType(String typ) {
        type = typ;
    }

    public String getType() {
        return type;
    }
}
