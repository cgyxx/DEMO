package demos;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;


public class demo {

    public static void main(String[] args) throws IOException {
        StringBuffer buffer = new StringBuffer();
        //获取文件通道
        FileChannel channel = FileChannel.open(Paths.get("d://linshi.pdf"), StandardOpenOption.READ);
        System.out.println("abc");
        //获取字节缓冲区
        ByteBuffer bb = ByteBuffer.allocate(1000);
        //读写操作
        while (channel.read(bb) != -1) {
            //切换读模式
            bb.flip();
            while (bb.position() < bb.limit()) {
                buffer.append(bb.get());
            }
            //清空缓存并切换到写模式
            bb.clear();
        }


    }

    public void countdl(){
        CountDownLatch latch = new CountDownLatch(2);

    }

}
