package Business;

public interface Document {

    public abstract void send(String email, String subject, String body) throws Exception;

}
