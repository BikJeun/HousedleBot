import java.util.HashMap;

public class UserEntity {
    String firstName;
    String lastName;
    String username;

    public UserEntity(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getUsername() {
        return username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object toFirebaseObject() {
        HashMap<String, Object> event = new HashMap<String, Object>();
        event.put("firstName", firstName);
        event.put("lastName", lastName);
        event.put("username", username);

        return event;
    }
}
