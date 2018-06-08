public class Main {

    public static void main(String[] args) {
        new Main();
    }

    Main() {
        //Stampa una lista vuota
        StringSList s1 = new StringSList();
        System.out.println(s1.toString());

        //Aggiunge "Ciao" all'inizio della lista
        s1 = s1.cons("Ciao");
        System.out.println(s1.toString());

        s1 = s1.cons("Bello").cons("Sei bello");
        System.out.println(s1.toString());

        System.out.println(s1.car());
        System.out.println(s1.cdr());
        StringSList s2 = new StringSList().cons("Nuova").cons("Lista");
        s1 = s1.append(s2);
        System.out.println(s1);
    }
}
