package util;

import java.util.concurrent.TimeUnit;

public class Timer {
    public static void transicao(int tempo) {
        try {
            TimeUnit.MILLISECONDS.sleep(tempo);
        } catch (InterruptedException ex) {
        }
    }
}
