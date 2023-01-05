import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame{
    public JPanel MainPanel;
    private JTextField linkField;
    private JCheckBox onlyAudioCheckBox;
    private JButton openFolderButton;
    private JButton downloadButton;

    public MainWindow() {
        downloadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean audio = false;
                if (onlyAudioCheckBox.isSelected()){
                    audio = true;
                }
                if (Main.OS.contains("Windows")){
                    Utils.DownloadForWindows(linkField.getText(), downloadButton, MainPanel, audio);
                }else{
                    Utils.DownloadForLinux(linkField.getText(), downloadButton, MainPanel, audio);
                }
                super.mouseClicked(e);
            }
        });
        openFolderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().open(new File(Main.YTDownload));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                super.mouseClicked(e);
            }
        });
    }
}
