class Message2 implements Runnable {
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
