package telran.multithreading;

public class Worker extends Thread {

    // Static - обязательно, иначе потоки будут работать с разными экземплярами объектов
    // и deadlock никак не получить
    private static final Object mutex1 = new Object();
    private static final Object mutex2 = new Object();

    @Override
    public void run() {
        // Такое количество итераций, чтобы статистически гарантировать deadlock
        for (int i = 0; i < 100000; i++) {
            f1();
            f2();
        }

    }

    private void f1() {
        synchronized (mutex1) {
            synchronized (mutex2) {

            }
        }
    }

    private void f2() {
        synchronized (mutex2) {
            synchronized (mutex1) {

            }

        }
    }
}
