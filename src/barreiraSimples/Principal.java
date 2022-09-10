package barreiraSimples;

public class Principal {
	public static void main(String[] args) throws InterruptedException {
		ThreadZero threadZero = new ThreadZero();
		threadZero.start();
		threadZero.join();
		System.out.println("Todas as Threads terminaram");
	}
}
