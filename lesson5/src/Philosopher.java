import java.util.Random;

import static java.lang.Thread.sleep;

public class Philosopher extends Thread{
    private String name;
    private int mealTime;
    private Object leftFork;
    private Object rightFork;

    public Philosopher(String name, Object leftFork, Object rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        mealTime = 0;
    }

    @Override
    public void run() {
        while (mealTime < 3){
            think();
            synchronized (leftFork){
                System.out.println(name + " взял левую вилку");
                synchronized (rightFork){
                    System.out.println(name + " взял правую вилку");
                    eat();
                    System.out.println(name + " отложил правую вилку");
                }
                System.out.println(name + " отложил левую вилку");
            }
        }
        System.out.println(name + " покушал три раза");
    }
    private void think(){
        System.out.println(name + " размышляет");
        try{
            sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private void eat() {
        System.out.println(name + " кушает");
        try{
            sleep(3000);
            System.out.println(name + " покушал");
            mealTime++;
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
