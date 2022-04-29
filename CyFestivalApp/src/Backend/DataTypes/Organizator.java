package Backend.DataTypes;

/**
 * This class handles the information about Organizators
 * Checks equality between Organizator objects
 */
public class Organizator {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    /**
     * Class constructor
     *
     * @param firstName   first and middle name of the organizator
     * @param lastName    last name of the organizator
     * @param email       email adress of the organizator
     * @param phoneNumber phone number of the organizator
     */
    Organizator(String firstName, String lastName, String email, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    /**
     * @param object Takes Organizator object. Look all parameters to decide equality
     * @return on success true, on failure false
     */
    @Override
    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Organizator other) {
            boolean isNameEqual = getFirstName().equals(other.getFirstName());
            boolean isLastNameEqual = getLastName().equals(other.getLastName());
            boolean isEmailEqual = getEmail().equals(other.getEmail());
            boolean isPhoneEqual = getPhoneNumber().equals(other.getPhoneNumber());

            if (isNameEqual && isLastNameEqual && isEmailEqual && isPhoneEqual) {
                result = true;
            }
        }

        return result;
    }

    /**
     * @return first and middle name of the organizator
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName name of the organizator
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return last name of the organizator
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName surname of the organizator
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return email of the organizator
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email email of the organizator
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return phone number of the organizator
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber phone number of the organizator
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
