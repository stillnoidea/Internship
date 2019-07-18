package tickets;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "traveler")
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Expose(serialize = false)
    private long id;

    private String fullName;
    private String passportNumber;
    private transient String name;
    private transient String surname;
    private transient String passport;

    public Traveler(){}

    Traveler(String name, String surname, String passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
        if (!passport.equals("")) {
            passportNumber = getSecretPassport();
        } else this.passport = passportNumber = null;
        if (!name.equals("") && !surname.equals("")) {
            fullName = getSecretFullName();
        } else this.name = this.surname = fullName = null;
    }

    Traveler(JsonObject traveler) {
        this.name = traveler.get("travelerName").getAsString();
        this.surname = traveler.get("travelerSurname").getAsString();
        this.passport = traveler.get("passportNumber").getAsString();
        fullName = getSecretFullName();
        passportNumber = getSecretPassport();
    }


    private String getSecretFullName() {
        if (name == null) {
            return null;
        }
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
        if (passport == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int size = passport.length();
        for (int i = 0; i < 6; i++) {
            sb.append("*");
        }
        sb.append(passport.charAt((size - 2))).append(passport.charAt((size - 1)));
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public String getSecuredFullName() {
        return fullName;
    }

    public String getSecuredPassport() {
        return passportNumber;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}