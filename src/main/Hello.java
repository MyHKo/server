import java.nio.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.ServerSocket;

public class Hello {
	public static void main(String[] args) throws IOException{
	
	ServerSocketChannel mainChannel = ServerSocketChannel.open();
	mainChannel.socket().bind(new InetSocketAddress(8080));
	mainChannel.configureBlocking(false);
	Selector selector = Selector.open();
	
	mainChannel.register(selector, SelectionKey.OP_ACCEPT);
	
	System.out.println("Listening on port 8080");
	
	while(true) {
		selector.select();
		
		Set<SelectionKey> keys = selector.selectedKeys();
		Iterator<SelectionKey> iterator = keys.iterator();
		
		while(iterator.hasNext()) {
			SelectionKey key = iterator.next();
			
			if(key.isAcceptable()) {
				ServerSocketChannel server = (ServerSocketChannel) key.channel();
				SocketChannel client = server.accept();
				client.configureBlocking(false);
				client.register(selector, SelectionKey.OP_READ);
			}
			if(key.isReadable()) {
				SocketChannel client = (SocketChannel) key.channel();
				ByteBuffer buffer = ByteBuffer.allocate(256);
				int bytesRead = client.read(buffer);
				
			}
			
			iterator.remove();
		}
	}
	}
}
