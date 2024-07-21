import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String LOG_PATH = "log.txt";

    List<ClientGUI> clientGUIList;
    JButton btnStart, btnStop;
    JTextArea log;
    boolean isServerWorking;
    public ServerWindow(){
        clientGUIList = new ArrayList<>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        log = new JTextArea();
        add(log);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                printLog("Сервер запущен!");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                while (!clientGUIList.isEmpty()){
                    disconnectClient(clientGUIList.get(clientGUIList.size()-1));
                }
                printLog("Сервер остановлен!");
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);

        add(panBottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    public boolean connectClient(ClientGUI clientGUI){
        if (isServerWorking){
            clientGUIList.add(clientGUI);
            printLog( clientGUI.tfLogin.getText() + " подключился к беседе");
            return true;
        } else {
            return false;
        }
    }

    public String getLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void disconnectClient(ClientGUI clientGUI){
        clientGUIList.remove(clientGUI);
        printLog( clientGUI.tfLogin.getText() + " покинул беседу");
        if (clientGUI != null){
            clientGUI.disconnectServer();
        }
    }

    public void message(String text){
        text += "";
        printLog(text);
        answerAll(text);
        saveInLog(text);
    }

    private void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.answer(text);
        }
    }

    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    private void printLog(String text){
        log.append(text + "\n");
    }
}