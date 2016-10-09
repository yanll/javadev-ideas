package sina.client;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by YANLL on 2016/08/05.
 */
public class FileHandler {
    @Test
    public void dof() throws IOException {
        FileInputStream fis = new FileInputStream("/var/sina.txt");
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        int i = 0;
        while ((line = br.readLine()) != null) {
            //FileUtil.log("/var/sina_.txt", nickname + "\n");
            System.out.println(line);
            i++;
        }
        br.close();
        isr.close();
        fis.close();
    }


}
