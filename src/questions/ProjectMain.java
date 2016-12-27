/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package questions;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Pratik
 */
public class ProjectMain {

    public static Login fr;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        fr = new Login();
        fr.dispose();
        fr.setUndecorated(true);
        fr.setSize(1366, 768);
        fr.setVisible(true);
    }
}
