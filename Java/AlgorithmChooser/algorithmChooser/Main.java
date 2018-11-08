package algorithmChooser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        while (true) {
            try {
                new SelectAlgorithm().run();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Il valore inserito non è valido!");
            } catch (NumberFormatException e) {
                System.err.println("Non hai inserito un numero!");
            }
        }
    }
}
