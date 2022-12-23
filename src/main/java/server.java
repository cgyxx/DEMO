import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class server {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 1.注册端口
        ServerSocket ss = new ServerSocket(8086);
        // 2.定义一个循环接收客户端的Socket连接请求
        // 初始化一个线程池对象
        SocketServerPoolHandler poolHandler = new SocketServerPoolHandler(3, 10);
        while (true) {
            Thread.sleep(5000);
            Socket socket = ss.accept();
            // 3.把Socket对象交给一个线程池进行处理
            // 把Socket封装成一个任务对象交给线程池处理
            Runnable target = new ServerRunnableTarget(socket);
            poolHandler.execute(target);
        }

    }
}
class SocketServerPoolHandler {
    /**
     * 1.创建一个线程池的成员变量用于存储一个线程池对 象
     */
    private ExecutorService executorService;

    /**
     * 2.创建这个类的时候就需要初始化线程池对象
     */
    public SocketServerPoolHandler(int maxThreadNum, int queueSize) {
        executorService = new ThreadPoolExecutor(3, maxThreadNum,
                120, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    /**
     * 3.提供一个方法来提交任务给线程池的任务队列来暂存,等着线程池来处理
     */
    public void execute(Runnable target) {
        executorService.execute(target);
    }
}

class ServerRunnableTarget implements Runnable {
    private Socket socket;

    public ServerRunnableTarget(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 处理接收的客户端Socket通信需求
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("服务端接收到:" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
