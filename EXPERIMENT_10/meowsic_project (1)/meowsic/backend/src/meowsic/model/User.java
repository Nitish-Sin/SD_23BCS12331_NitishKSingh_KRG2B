package meowsic.model;

public class User {

    private int    id;
    private String userId;
    private String password;
    private String displayName;
    private String email;

    public User() {}

    public User(String userId, String password, String displayName, String email) {
        this.userId      = userId;
        this.password    = password;
        this.displayName = displayName;
        this.email       = email;
    }

    // Getters & Setters
    public int    getId()          { return id; }
    public void   setId(int id)    { this.id = id; }

    public String getUserId()              { return userId; }
    public void   setUserId(String userId) { this.userId = userId; }

    public String getPassword()                  { return password; }
    public void   setPassword(String password)   { this.password = password; }

    public String getDisplayName()                     { return displayName; }
    public void   setDisplayName(String displayName)   { this.displayName = displayName; }

    public String getEmail()               { return email; }
    public void   setEmail(String email)   { this.email = email; }
}
