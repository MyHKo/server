public class threads{
	static int a = 10;
	public static void main(String[] args) {
		
		
		Runnable t1 = () -> {
			int temp = a;
			try {Thread.sleep(10);} catch (Exception e){} 
			a = temp + 1;
			System.out.println(a);
		};
		
		
		Thread worker = new Thread(t1);
		Thread worker2 = new Thread(t1);
		Thread worker3 = new Thread(t1);
		Thread worker4 = new Thread(t1);
		
		worker.start();
		worker2.start();
		worker3.start();
		worker4.start();
		
		while(true) {
			if(!(worker.isAlive() || worker2.isAlive() || worker3.isAlive() || worker4.isAlive())) {
				System.out.println(a);
				break;
			}
		}
		
		

	}
}