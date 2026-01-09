package dao;import java.io.*;
import java.time.LocalDate;
import java.io.*;
import java.time.LocalDate;

public class DailyTokenWithReset {

    private static final String FILE_NAME = "daily_token.txt";

    public static int nextToken() {
        LocalDate today = LocalDate.now();
        int lastToken = 0;
        LocalDate lastDate = null;

        try {
            File file = new File(FILE_NAME);

            // Read existing data
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                br.close();

                if (line != null) {
                    String[] parts = line.split(",");
                    lastDate = LocalDate.parse(parts[0]);
                    lastToken = Integer.parseInt(parts[1]);
                }
            }

            // If new day → reset token
            int newToken;
            if (lastDate == null || !today.equals(lastDate)) {
                newToken = 1;
            } else {
                newToken = lastToken + 1;
            }

            // Save updated token
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(today + "," + newToken);
            bw.close();

            return newToken;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("Token: " + nextToken());
        
    }
}




