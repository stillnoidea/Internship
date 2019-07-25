package pass;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "traveler")
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Expose(serialize = false)
    private long id;

    private String SecretFullName;
    private String SecretPassportNo;
    private transient String name;
    private transient String surname;
    private transient String passportNo;

    public Traveler() {
    }

    Traveler(String name, String surname, String passportNo) {
        this.name = name;
        this.surname = surname;
        this.passportNo = passportNo;
        setSecretPassportNo();
        setSecretFullName();
    }

//    Traveler(JsonObject traveler) {
//        this.name = traveler.get("travelerName").getAsString();
//        this.surname = traveler.get("travelerSurname").getAsString();
//        this.passportNo = traveler.get("SecretPassportNo").getAsString();
//        SecretFullName = encryptFullName();
//        SecretPassportNo = encryptPassportNo();
//    }

    private void setSecretFullName() {
        if (!name.equals("") && !surname.equals("")) {
            SecretFullName = encryptFullName();
        } else this.name = this.surname = SecretFullName = null;
    }

    private String encryptFullName() {
//        if (name == null) {
//            return null;
//        }
        int size = surname.length();
        StringBuilder sb = new StringBuilder();
        sb.append(name.charAt(0)).append(". ").append(surname.charAt(0));
        for (int i = 0; i < 5; i++) {
            sb.append("*");
        }
        sb.append(surname.charAt(size - 1));
        return sb.toString();
    }

    private void setSecretPassportNo() {
        if (!passportNo.equals("")) {
            SecretPassportNo = encryptPassportNo();
        } else this.passportNo = SecretPassportNo = null;
    }

    private String encryptPassportNo() {
//        if (passportNo == null) {
//            return null;
//        }
        StringBuilder sb = new StringBuilder();
        int size = passportNo.length();
        for (int i = 0; i < 6; i++) {
            sb.append("*");
        }
        sb.append(passportNo.charAt((size - 2))).append(passportNo.charAt((size - 1)));
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public String getSecuredFullName() {
        return SecretFullName;
    }

    public String getSecuredPassport() {
        return SecretPassportNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}