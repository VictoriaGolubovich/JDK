package server;

public interface Repository {
    String getHistory();

    void save(String text);
}
