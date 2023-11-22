public class Thread2 implements Runnable {
    private String prenom;

    public Thread2(String prenom) {
        this.prenom = prenom;
    }

    public static void main(String[] args) {
        new Thread1("Tata").start();
        Thread2 t3 = new Thread2("toto");
        new Thread(t3).start();
        new Thread(new Thread2("toto")).start();
    }

    @Override

    public void run() {
        System.out.println("Bonjour " + prenom);
    }

}
