import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private ServerWindow server;
    private boolean isServerWorking;
    private String name;
    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel panel;

    public ClientGUI(ServerWindow server){
        this.server = server;

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX() - 500, server.getY());

        add(createPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createBtn(), BorderLayout.SOUTH);

        setVisible(true);
    }

    public void answer(String text){
        appendLog(text);
    }

    private void connectServer() {
        if (server.connectClient(this)){
            appendLog("Вы успешно подключились!\n");
            panel.setVisible(false);
            isServerWorking = true;
            name = tfLogin.getText();
            String log = server.getLog();
            if (log != null){
                appendLog(log);
            }
        } else {
            appendLog("Подключение не удалось");
        }
    }

    public void disconnectServer() {
        if (isServerWorking) {
            panel.setVisible(true);
            isServerWorking = false;
            server.disconnectClient(this);
            appendLog("Вы были отключены от сервера!");
        }
    }

    public void message(){
        if (isServerWorking){
            String text = tfMessage.getText();
            if (!text.isEmpty()){
                server.message(name + ": " + text);
                tfMessage.setText("");
            }
        } else {
            appendLog("Нет подключения к серверу");
        }

    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    private Component createPanel(){
        panel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.0");
        tfPort = new JTextField("8181");
        tfLogin = new JTextField("Login");
        password = new JPasswordField("Password");
        btnLogin = new JButton("вход");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectServer();
            }
        });

        panel.add(tfIPAddress);
        panel.add(tfPort);
        panel.add(new JPanel());
        panel.add(tfLogin);
        panel.add(password);
        panel.add(btnLogin);

        return panel;
    }

    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createBtn() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    message();
                }
            }
        });
        btnSend = new JButton("отправить");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent windowEvent) {
        if (windowEvent.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectServer();
        }
        super.processWindowEvent(windowEvent);
    }

}
