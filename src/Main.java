import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class Main {

    public static String OS;
    public static String Home;
    public static String YTPath;
    public static String YTExecutable;
    public static String YTTempExecutable;
    public static String YTDownload;

    public static void main(String[] args){

        OS = System.getProperty("os.name");
        System.out.println(OS);

        if (OS.contains("Windows")){
            Home = System.getProperty("user.home");
            YTPath = Home+"\\.youtube-downloader";
            File file = new File(YTPath);
            if (!file.isDirectory()) {
                File theDir = new File(YTPath);
                if (!theDir.exists()){
                    theDir.mkdirs();
                }
            }

            try {
                ReadableByteChannel readChannel = Channels.newChannel(new URL("https://youtube-dl.org/downloads/latest/youtube-dl.exe").openStream());
                YTExecutable = YTPath+"\\youtube-dl.exe";
                YTTempExecutable = YTPath+"\\youtube-temp-dl.exe";
                YTDownload = YTPath+"\\Downloads";
                File ytdownload = new File(YTDownload);
                if(!ytdownload.isDirectory()) {
                    File theDir = new File(YTDownload);
                    if (!theDir.exists()){
                        theDir.mkdirs();
                    }
                }
                FileOutputStream fileOS = new FileOutputStream(YTExecutable);
                FileChannel writeChannel = fileOS.getChannel();
                writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Home = System.getProperty("user.home");
            YTPath = Home+"/.youtube-downloader";
            File file = new File(YTPath);
            if (!file.isDirectory()) {
                File theDir = new File(YTPath);
                if (!theDir.exists()){
                    theDir.mkdirs();
                }
            }

            try {
                ReadableByteChannel readChannel = Channels.newChannel(new URL("https://youtube-dl.org/downloads/latest/youtube-dl").openStream());
                YTExecutable = YTPath+"/youtube-dl";
                YTDownload = YTPath+"/Downloads";
                File ytdownload = new File(YTDownload);
                if(!ytdownload.isDirectory()) {
                    File theDir = new File(YTDownload);
                    if (!theDir.exists()){
                        theDir.mkdirs();
                    }
                }
                FileOutputStream fileOS = new FileOutputStream(YTExecutable);
                FileChannel writeChannel = fileOS.getChannel();
                writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        MainWindow mw = new MainWindow();
        mw.setTitle("Youtube Downloader");
        mw.setContentPane(mw.MainPanel);
        mw.setSize(400,300);
        mw.setVisible(true);
        mw.setResizable(false);
        mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}