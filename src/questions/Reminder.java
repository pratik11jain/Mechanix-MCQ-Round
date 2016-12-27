/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package questions;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import static questions.Client1.score;

/**
 * @author Pratik
 */
public class Reminder {
    public static Timer timer;
    public static int timerFlag = 0;
    int i = 0, j = 40;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, seconds * 1000);
    }


    class RemindTask extends TimerTask {


        @Override
        public void run() {

            if (i > -1) {
                Client1.jLabel8.setText(" " + (i--) + " ");

                Client1.jLabel5.setText(" " + (j) + " ");
            } else {
                i = 59;
                Client1.jLabel8.setText(" " + (i) + " ");
                Client1.jLabel5.setText(" " + (--j) + " ");
                if (j == -1) {
                    Client1.jLabel8.setText(" " + 0 + " ");
                    Client1.jLabel5.setText(" " + 0 + " ");

                    timerFlag = 1;
                    timer.cancel();
                    Score s = new Score();
                    //  ProjectMain.fr1.setVisible(false);
                    s.dispose();
                    s.setUndecorated(true);
                    s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    s.setSize(1366, 768);
                    s.setVisible(true);
                    s.setOpacity((float) 0.8);
                    String sentence;
                    try (Socket clientSocket = new Socket("10.1.33.51", 1111)) {
                        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        sentence = "" + Client1.score + " | " + Login.jTextField1.getText() + " | " + Login.jTextField2.getText() + " | " + Login.jTextField3.getText() + "\n";
                        outToServer.writeBytes(sentence + '\n');
                    }
                    catch (IOException ex) {
                        Logger.getLogger(Reminder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

}
