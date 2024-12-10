package aspect;

public class ExceptionManager {
    
    private static ExceptionManager instance;
    private ExceptionManager() {
    }

    public static ExceptionManager getInstance() {
        if (instance == null) {
            instance = new ExceptionManager();
        }
        return instance;
    }
    public void process(Exception exception) {
        exception.printStackTrace();
    }
}
