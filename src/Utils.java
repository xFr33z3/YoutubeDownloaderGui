import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Utils {
    public static void DownloadForLinux(String url, JButton downloadButton, JPanel MainPanel, boolean audio){
        try {
            String cmd = null;
            if (audio){
                cmd = "sh -c 'python3 "+Main.YTExecutable+" --extract-audio --audio-format mp3 --output \""+Main.YTDownload+"/%(title)s.%(ext)s\" "+url+"'";
            }else{
                cmd = "sh -c 'python3 "+Main.YTExecutable+" --output \""+Main.YTDownload+"/%(title)s.%(ext)s\" "+url+"'";
            }
            System.out.println(cmd);
            String finalCmd = cmd;
            new Thread(){
                @Override
                public void run(){
                    try {
                        downloadButton.setText("Downloading...");
                        downloadButton.setEnabled(false);
                        Process p = Runtime.getRuntime().exec(new String[]{"sh","-c", finalCmd}); p.waitFor();
                        downloadButton.setText("Download");
                        downloadButton.setEnabled(true);
                        Desktop.getDesktop().open(new File(Main.YTDownload));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void DownloadForWindows(String url, JButton downloadButton, JPanel MainPanel, boolean audio){
        try {
            String cmd = null;
            if (audio) {
                cmd = "cmd /c copy " + Main.YTExecutable + " " + Main.YTTempExecutable + " && " + Main.YTTempExecutable + " --extract-audio --audio-format mp3 --output \"" + Main.YTDownload + "/%(title)s.%(ext)s\" " + url;
            }else{
                cmd = "cmd /c copy " + Main.YTExecutable + " " + Main.YTTempExecutable + " && " + Main.YTTempExecutable + " --output \"" + Main.YTDownload + "/%(title)s.%(ext)s\" " + url;
            }
            System.out.println(cmd);
            String finalCmd = cmd;
            new Thread(){
                @Override
                public void run(){
                    try {
                        downloadButton.setText("Downloading...");
                        downloadButton.setEnabled(false);
                        Process p = Runtime.getRuntime().exec(new String[]{"cmd","/c", finalCmd}); p.waitFor();
                        downloadButton.setText("Download");
                        downloadButton.setEnabled(true);
                        Desktop.getDesktop().open(new File(Main.YTDownload));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
