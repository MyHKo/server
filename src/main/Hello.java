import java.nio.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.ServerSocket;

public class Hello {
	public static void main(String[] args) throws IOException{
	
	ServerSocketChannel server = ServerSocketChannel.open();
	server.socket().bind(new InetSocketAddress(8080));
	server.configureBlocking(false);
	Selector selector = Selector.open();
	
	server.register(selector, SelectionKey.OP_ACCEPT);
	
	System.out.println("Listening on port 8080");
	
	while(true) {
		selector.select();
		
		Set<SelectionKey> keys = selector.selectedKeys();
		Iterator<SelectionKey> iterator = keys.iterator();
		
		while(iterator.hasNext()) {
			SelectionKey key = iterator.next();
			System.out.println("Request has been received");
			iterator.remove();
		}
	}
	}
}
