public class Thread1 extends Thread {
    private String nameT;
    public Thread1(String nameT){
        this.nameT = nameT;
    }
    public static void main(String[] args) {
        new Thread1("Tata").start();
    }

    @Override

    public void run() {
        System.out.println("Bonjour " + nameT);
    }
}