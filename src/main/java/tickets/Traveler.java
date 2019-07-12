package tickets;

class Traveler {
    private String securedFullName;
    private String securedPassport;
    private transient String name;
    private transient String surname;
    private transient String passport;

    Traveler(String name, String surname, String passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
        securedFullName = getSecretFullName();
        securedPassport = getSecretPassport();
    }

    private String getSecretFullName() {
        int size = surname.length();
        StringBuilder sb = new StringBuilder();
        sb.append(name.charAt(0)).append(". ").append(surname.charAt(0));
        for (int i = 0; i < 5; i++) {
            sb.append("*");
        }
        sb.append(surname.charAt(size - 1));
        return sb.toString();
    }

    private String getSecretPassport() {
        StringBuilder sb = new StringBuilder();
        int size = passport.length();
        for (int i = 0; i < 6; i++) {
            sb.append("*");
        }
        sb.append(passport.charAt(size - 2)).append(passport.charAt(size - 1));
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public String getSecuredFullName() {
        return securedFullName;
    }

    public String getSecuredPassport() {
        return securedPassport;
    }


}