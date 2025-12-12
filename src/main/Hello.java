import java.nio.*;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.ServerSocket;

public class Hello {
	public static void main(String[] args) throws IOException{
	
	ServerSocketChannel server = ServerSocketChannel.open();
	server.socket().bind(new InetSocketAddress(8080));
	server.configureBlocking(false);
	Selector selector = Selector.open();
	System.out.println("Listening on a port 8080");	
	}
}
