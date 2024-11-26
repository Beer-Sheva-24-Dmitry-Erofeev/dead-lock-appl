package telran.multithreading;

public class Worker extends Thread {

    // Static - обязательно, иначе потоки будут работать с разными экземплярами объектов
    // и deadlock никак не получить
    private static final Object mutex1 = new Object();
    private static final Object mutex2 = new Object();

    @Override
    public void run() {
        f1();
        f2();
    }

    private void f1() {
        synchronized (mutex1) {
            try {
                sleep(0);
            } catch (InterruptedException e) {
            }
            synchronized (mutex2) {

            }
        }
    }

    private void f2() {
        synchronized (mutex2) {
            try {
                sleep(100);
                // "Работа" (sleep) нужна именно в методе f2(), Так мы отсрочиваем выполнение f2() и второй поток
                // успевает в своём варианте f1() попытаться захватить f2(), что и вызывает deadlock.
                // Во всяком случае я так это понимаю.
            } catch (InterruptedException e) {
            }
            synchronized (mutex1) {

            }

        }
    }
}
