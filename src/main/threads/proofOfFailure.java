import java.util.List;
import java.util.ArrayList;

public class proofOfFailure{
	static int a = 0;
	public static void main(String[] args){
		Runnable task = new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 1000; i++) {
					a++;
				}
			}
		};
		
		ArrayList<Thread> workers = new ArrayList();
		
		for (int i = 0; i < 1000; i++) {
			Thread t = new Thread(task);
			workers.add(t);
			t.start();
		}
		
		for(Thread t : workers) {
			try {t.join();} catch(InterruptedException e) {}
		}
		
		System.out.println(a);
	}
}