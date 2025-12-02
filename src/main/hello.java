import java.io*;
import java.net;
import java.io.BufferedReader;
import java.net.ServerSocket;

public class hello() {
	publis static void main(String[] args) throws IOExecption {
	
	ServerSocker server = new ServerSocker(8080);
	System.out.println("Listening on a port 8080");
	
	while(true) {
		try(Socket clientSocket = server.accept()){
			
			System.out.println("client connected");
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(clientSocket.get)
			);
			
			String line = in.readLine();
			while(line != null && !line.isEmpty()) {
				System.out.println(line);
				line = in.readLine();
			}
			
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
			out.println("HTTP/1.1 200 OK");
			out.println("Content-type: text/plain");
			out.println("Connection: close");
			out.println("");
			out.println("Hello!");
			out.flush();
			}
		}
	}
}