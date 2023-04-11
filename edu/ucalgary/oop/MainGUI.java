/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@author Rohil Dhillon <a href="mailto:Rohil.Dhillon@ucalgary.ca">
Rohil.Dhillon@ucalgary.ca</a>
@author Mehrnaz Zafari <a href="mailto:mehrnaz.zafari@ucalgary.ca">
mehrnaz.zafari@ucalgary.ca</a>
@author Abhyudai Singh <a href="mailto:abhyudai.singh@ucalgary.ca">
abhyudai.singh@ucalgary.ca</a>
@version 1.8
@since 1.0
*/
package edu.ucalgary.oop;

import javax.swing.*;

import edu.ucalgary.oop.Schedule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * This class creates a graphical user interface (GUI) for generating schedules
 * for a fictional animal hospital. The GUI is built using the Swing library in Java.
 */
public class MainGUI {
    private JFrame frame;
    private JButton generateScheduleButton;
    private boolean allConfirmed;

    /**
     * Constructor that initializes the frame and sets its properties such as size and close operation.
     * It then creates a JPanel with a BoxLayout and adds it to the frame. The panel is divided into five regions
     * using the BorderLayout: north, south, east, west, and center. It also adds a JButton labeled "Generate Schedule"
     * to the top panel of the JPanel and a JTextArea below it to provide instructions.
     */
    public MainGUI () {
        frame = new JFrame("Schedule Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);

        //mainPanel declared with Borderlayout which divides the container into five region, N, S, E, W, and Center.
        JPanel mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(layout);
        frame.add(mainPanel);

        // Top panel with Generate Schedule button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        generateScheduleButton = new JButton("Generate Schedule");
        topPanel.add(generateScheduleButton);

        //Adding topPanel to the top region of mainPanel
        mainPanel.add(topPanel);

        //Adding the instructions text field
        JTextArea textArea = new JTextArea();
        textArea.setText("Instructions: \n1. Click the Generate Schedule Button to make tomorrow's schedule.");
        textArea.append("\n");
        textArea.append("\n2.If a backup volunteer is required for completing tasks set out in the schedule then you will be prompted to do so via a dialogue box, if you do not click OK and click any other button you will be prompted again.");
        textArea.append("\n");
        textArea.append("\n3.If there are too many tasks for a specific hour, their start hour may need to be changed. This will be handled in the command line terminal on your PC/Mac");
        textArea.append("\n");
        textArea.append("\n4. After you finish changing the necessary start hours in the terminal, you can return to the GUI.");
        textArea.append("\n");
        textArea.append("\n5. If the schedule is generated you will see a prompt.");
        textArea.append("\n");
        textArea.append("\n6. Check your working directory for a file called schedule_TodaysDate.txt, that is the final schedule!");
        
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        //Adding the textfield to the main panel
        mainPanel.add(textArea);
        
        //Prompt to read instructions
        JOptionPane.showMessageDialog(null, "READ THE INSTRUCTIONS CAREFULLY \n          CLICK OK TO CONTINUE", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE);


         /**
         * ActionListener method that is called when the "Generate Schedule" button is clicked.
         * It generates a schedule and saves it to a text file. It prompts the user to confirm the backup volunteer
         * for the schedule before saving it.
         * @param e An ActionEvent object representing the action that was performed.
         */
        generateScheduleButton.addActionListener(new ActionListener() {
            /**
             * This method is called when an action is performed by the user. It generates a
             * schedule and saves it to a text file.
             * It prompts the user to confirm the back-up volunteer for the schedule before
             * saving it.
             * 
             * @param e An ActionEvent object representing the action that was performed.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the methods to generate the schedule and save the text file
                try {
                    Schedule schedule = new Schedule();
                    schedule.retrieveAnimals();
                    schedule.retrieveTreatments();
                    schedule.estimateTimeUsed();
                    schedule.buildSchedule();

                    allConfirmed = false;

                    int counter = 0;
                    String times = "";
                

                    for(boolean value : schedule.getVolunteerNeeded()) {
                        if(value == true){
                            times += Integer.toString(counter) + ":00" + ", ";
                        }
                        counter++;
                    }
                    
                    times = "A back-up volunteer is required for the following hours: " + "\n" + times;
                    times = times.substring(0, times.length() - 2);


                    while(allConfirmed == false){
                        int res = JOptionPane.showOptionDialog(null, times, "Confirm Volunteer", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                        if(res == -1){
                            JOptionPane.showMessageDialog(frame, "Confirmation from the back-up volunteer is required to build the schedule", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        if(res == 0){
                            allConfirmed = true;
                        }
                    }
                    schedule.formatSchedule();

                    JOptionPane.showMessageDialog(frame, "Schedule generated and saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "There was an error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        frame.setVisible(true);
    }

    /**
    * Main method that creates an instance of the MainGUI class and runs it on the Event Dispatch Thread.
    * @param args The command-line arguments passed to the program.
    */
    public static void main(String[] args) {
        /**
         * Runs the GUI construction and display method.
         */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI();
            }
        });
    }
}

