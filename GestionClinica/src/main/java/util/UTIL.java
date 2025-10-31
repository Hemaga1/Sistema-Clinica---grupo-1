package util;

import java.util.Random;

public class UTIL {
    private static Random random = new Random();

    public static void tiempoMuerto() {
        try {
            Thread.sleep(1000 + random.nextInt(2000)); //entre 1 y 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
