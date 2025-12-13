import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;
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
	ByteBuffer buffer = ByteBuffer.allocate(256);
	
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
				int bytesRead = client.read(buffer);
				
				if(bytesRead == -1) {
					client.close();
				}
				else {
					buffer.flip();
					String message = StandardCharsets.UTF_8.decode(buffer).toString();
					System.out.println(message);
					buffer.clear();
				}
			}
			
			iterator.remove();
		}
	}
	}
}
