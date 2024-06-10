package task.core.model;

public class UserRecord {
    private String firstName;
    private String secondName;
    private String lastName;
    private String birthDate;
    private Long phoneNumber;
    private Character gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + secondName + ", "
                + birthDate + ", " + phoneNumber + ", "  + gender;
    }
}
