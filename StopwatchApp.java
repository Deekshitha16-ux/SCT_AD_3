import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchApp extends JFrame {
    private JLabel timeLabel;
    private JButton startButton, pauseButton, resetButton;
    private Timer timer;
    private long startTime, elapsedTime;
    private boolean running = false;

    public StopwatchApp() {
        setTitle("Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Display
        timeLabel = new JLabel("00:00:000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        add(timeLabel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        resetButton = new JButton("Reset");
        
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Timer
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                elapsedTime = currentTime - startTime;
                updateDisplay(elapsedTime);
            }
        });

        // Start button
        startButton.addActionListener(e -> {
            if (!running) {
                startTime = System.currentTimeMillis() - elapsedTime;
                timer.start();
                running = true;
            }
        });

        // Pause button
        pauseButton.addActionListener(e -> {
            if (running) {
                timer.stop();
                running = false;
            }
        });

        // Reset button
        resetButton.addActionListener(e -> {
            timer.stop();
            running = false;
            elapsedTime = 0;
            updateDisplay(elapsedTime);
        });
    }

    private void updateDisplay(long time) {
        long minutes = (time / 60000) % 60;
        long seconds = (time / 1000) % 60;
        long milliseconds = time % 1000;

        timeLabel.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StopwatchApp stopwatch = new StopwatchApp();
            stopwatch.setVisible(true);
        });
    }
}
