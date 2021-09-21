package Business;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PswEncryption {
    
    private String password;
    private String hashed;

    public PswEncryption() {
        this.password = "";
        this.hashed = "";
    }

    public void setPassword(String password) {
        this.password = password;
        this.hashed = BCrypt.hashpw(this.password, BCrypt.gensalt(10));
    }

    public void setOnlyPassword(String onlyPassword) {
        this.password = onlyPassword;
    }

    public void setOnlyHashed(String onlyHashed) {
        this.hashed = onlyHashed;
    }

    public String getHashed() {
        return hashed;
    }
    
    public boolean check(String password) {
        if (BCrypt.checkpw(password, hashed)) {
            return true;
        } else {
            return false;
        }
    }
}
