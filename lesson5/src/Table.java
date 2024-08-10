public class Table extends Thread{
    private Philosopher[] philosophers;

    public Table() {
        philosophers = new Philosopher[5];
        Object[] forks = new Object[5];
        for (int i=0; i<forks.length; i++){
            forks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];
            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher("Philosopher" + String.valueOf(i + 1), rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher("Philosopher" + String.valueOf(i + 1), leftFork, rightFork);
            }
        }
    }

    @Override
    public void run() {
        for (Philosopher philosopher : philosophers){
            philosopher.start();
        }
    }
}
