public class threads{
	public static void main(String[] args) {
		
		int a = 10;
		
		Runnable t1 = () -> {
			a++;
			System.out.println("a is " + a + " and it was updated by " + Thread.currentThread().getName());
		};
		
		Thread worker = new Thread(t1);
		Thread worker2 = new Thread(t1);
		
		worker.start();
		worker2.start();
		

	}
}