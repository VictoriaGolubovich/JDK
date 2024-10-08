package task3;

public class Pair <T, U>{
    private T t;
    private U u;

    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }
    public T getFirst(){
        return t;
    }
    public U getSecond(){
        return u;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "t=" + t +
                ", u=" + u +
                '}';
    }
}
