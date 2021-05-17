class Message implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println((System.currentTimeMillis() - Main.time) / 1000 + " sec");
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Message2.counterSec++;
        }
    }
}
