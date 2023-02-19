

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FileFrame extends JFrame {

    public static FileFrame fileFrame = null;
    public JButton selectFile = new JButton("选择文件");
    public JTextArea jta = new JTextArea();

    public FileFrame() {
        initComponents();
        setListeners();
    }

    public void initComponents() {
        setSize(800, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(selectFile);

        JPanel jtaPanel = new JPanel(new BorderLayout());
        jtaPanel.add(jta, BorderLayout.CENTER);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(jtaPanel, BorderLayout.CENTER);

        add(panel);
    }

    public void setListeners() {
        selectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();

                String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
                try {
                    UIManager.setLookAndFeel(lookAndFeel);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                    unsupportedLookAndFeelException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(jfc);//使设置得界面风格生效

                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                jfc.addChoosableFileFilter(new FileNameExtensionFilter( "zip(*.zip, *.rar)", "zip", "rar"));
                jfc.setFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
                jfc.setDialogTitle("选择文件");
                jfc.setCurrentDirectory(new File("."));
                int state = jfc.showOpenDialog(null);
                if (state == JFileChooser.APPROVE_OPTION) {
                    File file = jfc.getSelectedFile();
                    String filePath = file.getPath();
                    jta.setText(filePath);
                    if (filePath.toLowerCase().endsWith("zip")) {
                        try {
                            InputStream inputStream = new FileInputStream(file);
                            ZipInputStream zipInputStream = new ZipInputStream(inputStream, Charset.forName("GBK"));
                            ZipEntry entry;
                            while ((entry = zipInputStream.getNextEntry()) != null) {
                                if (!entry.isDirectory()) {
                                    BufferedInputStream bs = new BufferedInputStream(zipInputStream);
                                    System.out.println(entry.getName());
                                    System.out.println();
                                    byte[] bytes = new byte[(int) entry.getSize()];
                                    bs.read(bytes, 0, (int) entry.getSize());
                                    for (byte b : bytes) {
                                        System.out.print(b);
                                    }
                                    System.out.println(bytes.length + "");
                                }
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } finally {

                        }
                    } else if (filePath.toLowerCase().endsWith("rar")) {
                        Archive archive = null;
                        OutputStream outputStream = null;
                        try {
                            archive = new Archive(file);
                            FileHeader fileHeader;
                            while( (fileHeader = archive.nextFileHeader()) != null){
                                if(fileHeader.isDirectory()){
                                }else{
                                    System.out.println(fileHeader.getFileNameString());
                                    /*outputStream = new FileOutputStream(new File(filePath + File.separator + fileHeader.getFileNameString().trim()));
                                    archive.extractFile(fileHeader, outputStream);*/
                                }
                            }
                        } catch (RarException | IOException e1) {
                            e1.printStackTrace();
                        }finally {
                            try {
                                if(archive != null){
                                    archive.close();
                                }
                                if(outputStream != null){
                                    outputStream.close();
                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }
    public static FileFrame getInstance() {
        if (null == fileFrame) {
            fileFrame = new FileFrame();
        }
        return fileFrame;
    }

    public static void main(String[] args) {
        FileFrame fileFrame = FileFrame.getInstance();
        fileFrame.setVisible(true);
    }
}
