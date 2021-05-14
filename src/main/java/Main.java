import java.util.LinkedList;
import java.util.List;

public class Main {

    public static final long time = System.currentTimeMillis();
    public static volatile List<String> strLine = new LinkedList();


    public static void main(String[] args) {
        task1();
        task2();


    }

    public static void task1() {
        new Thread(new Massage()).start();
        new Thread(new Massage2()).start();
    }

    static class Massage implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println((System.currentTimeMillis() - Main.time) / 1000 + " sec");
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Massage2.counterSec++;
            }
        }
    }

    static class Massage2 implements Runnable {
        static volatile long counterSec;

        @Override
        public void run() {
            while (true) {
                if (counterSec % 5 == 0) {
                    System.out.println(("Прошло " + counterSec + " секунд"));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void task2() {
        /*
        систематизировал, для изменения колличества чисел в заданиии 2
        нужно только задать значение amountOfNumbers
         */
        final int amountOfNumbers = 15;

        Thread D = new Thread(new Runnable() {
            @Override
            public void run() {
                number(amountOfNumbers);
            }
        });
        D.start();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                fizz();
            }
        });
        A.start();
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                buzz();
            }
        });
        B.start();
        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                fizzbuzz();
            }
        });
        C.start();

        try {
            A.join();
            B.join();
            C.join();
            D.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(strLine.toString());
    }

    public static void number(int amountOfNumbers) {
        for (int i = 0; i < amountOfNumbers; i++) {
            Main.strLine.add(String.valueOf(i + 1));
        }
    }

    public static synchronized void fizz() {
        for (int i = 0; i < Main.strLine.size(); i++) {
            if ((i + 1) % 3 == 0) {
                Main.strLine.set(i, "fizz");
            }
        }
    }

    public static synchronized void buzz() {
        for (int i = 0; i < Main.strLine.size(); i++) {
            if ((i + 1) % 5 == 0) {
                Main.strLine.set(i, "buzz");
            }
        }
    }

    public static synchronized void fizzbuzz() {
        for (int i = 0; i < Main.strLine.size(); i++) {
            if ((i + 1) % 3 == 0 && (i + 1) % 5 == 0) {
                Main.strLine.set(i, "fizzbuzz");
            }
        }
    }
}