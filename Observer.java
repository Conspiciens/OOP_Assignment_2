import java.util.List;

public interface Observer {
    
    public void update(List<User> listToRender, List<String> message);
}
