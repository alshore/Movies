package com.andrew;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MovieGui extends JFrame{
    private JPanel rootPanel;
    private JLabel label1;
    public JTextField titleField;
    public JTextField yearField;
    private JLabel label2;
    public JTextField directorField;
    private JLabel label3;
    private JLabel label4;
    public JTextField actorField;
    private JLabel label5;
    public JTextField genreField;
    public JButton searchButton;
    private JButton closeButton;
    private InputStream response = null;

    public void createUIComponents() {
        String url = "http://www.omdbapi.com/?";
        String charset = "UTF-8";
        String title = titleField.getText();
        String director = directorField.getText();
        String year = yearField.getText();
        String actor = actorField.getText();
        String genre = genreField.getText();
        String query = "";
        if (title != null) {
            query += "t=" + title;
        }
        if (director != null) {
            query += "&" + director;
        }
        if (year != null) {
            query += "&y=" + year;
        }
        if (actor != null) {
            query += "&" + actor;
        }
        if (genre != null) {
            query += "&" + genre;
        }
        query += "&tomatoes=true&plot=short&r=json";
        String javaVersion = "Java/" + System.getProperty("java.version");
        String userAgent = System.getProperty("http.agent") == null ? javaVersion : System.getProperty("http.agent") + " " + javaVersion;
        try {
            URLConnection connection = new URL(url + query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe);
        }
    }
    MovieGui() {
        setContentPane(rootPanel);
        pack();
        setVisible(true);
        createUIComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getParent(), response);
                System.out.println(response);
            }
        });
    }
}
