public class threads{
	public static void main(String[] args) {
		
		Runnable t1 = () -> {
			System.out.println("Hello from Thread " + Thread.currentThread().getName());
		};
		Runnable t2 = () -> {
			System.out.println("Hello from Thread " + Thread.currentThread().getName());
		};
		Runnable t3 = () -> {
			System.out.println("Hello from Thread " + Thread.currentThread().getName());
		};
		
		Thread worker = new Thread(t1);
		Thread worker2 = new Thread(t2);
		Thread worker3 = new Thread(t3);
		
		worker.start();
		worker2.start();
		worker3.start();
		
		System.out.println("Hello from the main Thread");
	}
}