import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String FILE_NAME = "ice_age_start_time.txt";
    private static final long ICE_AGE_DURATION = TimeUnit.DAYS.toMillis(365 * 50000); // 50,000 years

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(timeLabel);

        long startTime = getStartTime();
        long nextIceAgeTime = startTime + ICE_AGE_DURATION;
        boolean[] showCurrentTime = {true};

        Timer timer = new Timer(1000, e -> {
            if (showCurrentTime[0]) {
                String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                timeLabel.setText(currentTime);
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                long timeUntilIceAge = nextIceAgeTime - currentTimeMillis;

                long years = TimeUnit.MILLISECONDS.toDays(timeUntilIceAge) / 365;
                long days = TimeUnit.MILLISECONDS.toDays(timeUntilIceAge) % 365;
                long hours = TimeUnit.MILLISECONDS.toHours(timeUntilIceAge) % 24;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(timeUntilIceAge) % 60;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(timeUntilIceAge) % 60;

                String iceAgeTime = String.format("%d years, %d days, %02d:%02d:%02d", years, days, hours, minutes, seconds);
                timeLabel.setText(iceAgeTime);
            }
        });
        timer.start();

        Timer switchTimer = new Timer(10000, e -> showCurrentTime[0] = !showCurrentTime[0]);
        switchTimer.start();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    frame.dispose();
                    System.exit(1);
                }
            }
        });

        frame.setVisible(true);
    }

    private static long getStartTime() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return Long.parseLong(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        long startTime = System.currentTimeMillis();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(Long.toString(startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return startTime;
    }
}