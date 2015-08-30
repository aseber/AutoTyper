import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Fri Mar 02 09:22:44 EST 2012
 */



/**
 * @author Austin Seber
 */
public class MainWindow extends JFrame {
	
	public MainWindow() {
	
		initComponents();
	
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new MainWindow();
		frame.setVisible(true);
		System.out.println("Creating Main GUI Window.");
		
	}
	
	private static Typer typer = null;
	private static boolean running = false;
	private static boolean UnlimitedRepeats = false;
	private static boolean TimedRepeats = false;
	private static boolean RandomizeWordsPerMinute = false;
	private static boolean StartOnLoseFocus = false;
	private static boolean EndOnGainFocus = false;
	private static int RepeatCount = 0;
	private static int WordsPerMinute = 50;
	private static int RandomizedWordsPerMinute = 0;
	private static double WaitTime = 1000;
	private static double TimedRepeatLength = 30000;
	private static String REPEAT_COUNT = "Please enter the desired repeat count.";
	private static String TIMED_REPEAT_LENGTH = "Please enter the desired time to repeat for.";
	private static String WORDS_PER_MINUTE = "Please enter the desired words per minute.";
	private static String WAIT_TIME = "Please enter the desired wait time.";
	private static String RANDOMIZE_WORDS_PER_MINUTE = "Please enter ther desired randomizer.";
	private static String DEFAULT_TYPING_FIELD_TEXT = "Type here";
	private static String VERSION = "1.1.1";
	
	private void FileMenu_New(ActionEvent e) {

		reset();
	
	}

	private void FileMenu_Close(ActionEvent e) {
		
		System.out.println("Closing Main GUI Window.");
		System.exit(0);
	
	}

	private void SettingsMenu_RCount_UnlimitedRepeats(ActionEvent e) {
		
		if (SettingsMenu_RCount_UnlimitedRepeats.getState() == true) {
		
			UnlimitedRepeats = true;
			TimedRepeats = false;
			SettingsMenu_RCount_Amount.setEnabled(false);
			SettingsMenu_RCount_TimedRepeats.setEnabled(false);
			SettingsMenu_RCount_TimedAmount.setEnabled(false);
			SettingsMenu_RCount_TimedRepeats.setState(false);
			return;
		
		} else {
			
			UnlimitedRepeats = false;
			SettingsMenu_RCount_Amount.setEnabled(true);
			SettingsMenu_RCount_TimedRepeats.setEnabled(true);
			return;
			
		}
	
	}

	private void SettingsMenu_RCount_TimedRepeats(ActionEvent e) {
		
		if (SettingsMenu_RCount_TimedRepeats.getState() == true) {
			
			TimedRepeats = true;
			UnlimitedRepeats = false;
			SettingsMenu_RCount_TimedAmount.setEnabled(true);
			SettingsMenu_RCount_UnlimitedRepeats.setEnabled(false);
			SettingsMenu_RCount_Amount.setEnabled(false);
			SettingsMenu_RCount_UnlimitedRepeats.setState(false);
			return;
		
		} else {
			
			TimedRepeats = false;
			SettingsMenu_RCount_TimedAmount.setEnabled(false);
			SettingsMenu_RCount_UnlimitedRepeats.setEnabled(true);
			SettingsMenu_RCount_Amount.setEnabled(true);
			return;
			
		}
		
	}
	
	private void SettingsMenu_WPM_Randomizer(ActionEvent e) {
		
		if (RandomizeWordsPerMinute == true) {
			
			RandomizeWordsPerMinute = false;
			SettingsMenu_WPM_RandomizeAmount.setEnabled(false);
			return;
			
		} else {
			
			RandomizeWordsPerMinute = true;
			SettingsMenu_WPM_RandomizeAmount.setEnabled(true);
			return;
			
		}
		
	}
	
	private void SettingsMenu_WaitTime_StartWhenLoseFocus(ActionEvent e) {

		if (SettingsMenu_WaitTime_StartWhenLoseFocus.getState() == true) {
			
			StartOnLoseFocus = true;
			return;
		
		} else {
			
			StartOnLoseFocus = false;
			return;
			
		}	
	
	}

	private void SettingsMenu_WaitTime_EndWhenGainFocus(ActionEvent e) {
		
		if (SettingsMenu_WaitTime_EndWhenGainFocus.getState() == true) {
			
			EndOnGainFocus = true;
			return;
			
		} else {
			
			EndOnGainFocus = false;
			return;
			
		}
		
	}

	private void MainWindowGainFocus(WindowEvent e) {
		
		if (EndOnGainFocus == true && running == true) {
			
			stopTyping();
			
		}
		
	}

	private void MainWindowLostFocus(WindowEvent e) {

		if (StartOnLoseFocus == true && running == false) {
			
			startTyping();
			
		}
		
	}
	
	private void SettingsMenu_RCount_Amount(ActionEvent e) {
		
		System.out.println("Creating Repeat Count Dialog.");
		createDialog(REPEAT_COUNT,RepeatCount);
		System.out.println("Repeat Count now set to: " + RepeatCount + " loop(s)");
		
	}

	private void SettingsMenu_RCount_TimedAmount(ActionEvent e) {
		
		System.out.println("Creating Timed Repeat Length Dialog.");
		createDialog(TIMED_REPEAT_LENGTH,TimedRepeatLength);
		System.out.println("Timed Repeat Length now set to: " + Double.valueOf(TimedRepeatLength/1000) + " seconds(s)");
		
	}
	
	private void SettingsMenu_WPM_Amount(ActionEvent e) {
		
		System.out.println("Creating Words Per Minute Dialog.");
		createDialog(WORDS_PER_MINUTE,WordsPerMinute);
		System.out.println("Words Per Minute now set to: " + WordsPerMinute + " WPM");

	}
	
	private void SettingsMenu_WaitTime_Amount(ActionEvent e) {
		
		System.out.println("Creating Wait Time Dialog.");
		createDialog(WAIT_TIME,WaitTime);
		System.out.println("Wait Time now set to: " + Double.toString(WaitTime/1000) + " second(s)");
	
	}

	private void SettingsMenu_WPM_RandomizeAmount(ActionEvent e) {
		
		System.out.println("Creating Randomizer Amount Dialog.");
		createDialog(RANDOMIZE_WORDS_PER_MINUTE,RandomizedWordsPerMinute);
		System.out.println("Randomizer Amount now set to: +/- " + RandomizedWordsPerMinute + " WPM");
	
	}
	
	private void TypeKey(ActionEvent e) {
		
		if (running == true) {
			
			stopTyping();
			return;
			
		} else {
			
			startTyping();
			return;
			
		}
		
	}

	private void TypingFieldFocusGained(FocusEvent e) {
		
		TypingField.setForeground(Color.BLACK);
		
		if (TypingField.getText().equals(DEFAULT_TYPING_FIELD_TEXT)) {
		
			TypingField.setText("");
			
		}
	
	}

	private void TypingFieldFocusLost(FocusEvent e) {
	
		if (TypingField.getText().equals("") || TypingField.getText().equals(DEFAULT_TYPING_FIELD_TEXT)) {
			
			TypingField.setForeground(new Color(150,150,150));
			TypingField.setText(DEFAULT_TYPING_FIELD_TEXT);
			
		}
	
	
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Austin Seber
		menuBar2 = new JMenuBar();
		FileMenu = new JMenu();
		FileMenu_New = new JMenuItem();
		FileMenu_Close = new JMenuItem();
		SettingsMenu = new JMenu();
		SettingsMenu_RCount = new JMenu();
		SettingsMenu_RCount_UnlimitedRepeats = new JCheckBoxMenuItem();
		SettingsMenu_RCount_TimedRepeats = new JCheckBoxMenuItem();
		SettingsMenu_RCount_Amount = new JMenuItem();
		SettingsMenu_RCount_TimedAmount = new JMenuItem();
		SettingsMenu_WPM = new JMenu();
		SettingsMenu_WPM_Randomizer = new JCheckBoxMenuItem();
		SettingsMenu_WPM_Amount = new JMenuItem();
		SettingsMenu_WPM_RandomizeAmount = new JMenuItem();
		SettingsMenu_WaitTime = new JMenu();
		SettingsMenu_WaitTime_StartWhenLoseFocus = new JCheckBoxMenuItem();
		SettingsMenu_WaitTime_EndWhenGainFocus = new JCheckBoxMenuItem();
		SettingsMenu_WaitTime_Amount = new JMenuItem();
		scrollPane1 = new JScrollPane();
		TypingField = new JTextArea();
		TypeKey = new JButton();
		DesignerPlaque = new JMenuItem();

		//======== this ========
		setBackground(new Color(100, 100, 100));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("AutoTyper");
		setResizable(false);
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				MainWindowGainFocus(e);
			}
			@Override
			public void windowLostFocus(WindowEvent e) {
				MainWindowLostFocus(e);
			}
		});
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"14dlu, 55dlu, 177dlu, 14dlu",
			"2*(14dlu), 109dlu, 27dlu, $lgap, default"));

		//======== menuBar2 ========
		{

			//======== FileMenu ========
			{
				FileMenu.setText("File");

				//---- FileMenu_New ----
				FileMenu_New.setText("New");
				FileMenu_New.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FileMenu_New(e);
					}
				});
				FileMenu.add(FileMenu_New);

				//---- FileMenu_Close ----
				FileMenu_Close.setText("Close");
				FileMenu_Close.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FileMenu_Close(e);
					}
				});
				FileMenu.add(FileMenu_Close);
			}
			menuBar2.add(FileMenu);

			//======== SettingsMenu ========
			{
				SettingsMenu.setText("Settings");

				//======== SettingsMenu_RCount ========
				{
					SettingsMenu_RCount.setText("Repeat Count");

					//---- SettingsMenu_RCount_UnlimitedRepeats ----
					SettingsMenu_RCount_UnlimitedRepeats.setText("Unlimited repeats");
					SettingsMenu_RCount_UnlimitedRepeats.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_RCount_UnlimitedRepeats(e);
						}
					});
					SettingsMenu_RCount.add(SettingsMenu_RCount_UnlimitedRepeats);

					//---- SettingsMenu_RCount_TimedRepeats ----
					SettingsMenu_RCount_TimedRepeats.setText("Timed Repeats");
					SettingsMenu_RCount_TimedRepeats.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_RCount_TimedRepeats(e);
						}
					});
					SettingsMenu_RCount.add(SettingsMenu_RCount_TimedRepeats);

					//---- SettingsMenu_RCount_Amount ----
					SettingsMenu_RCount_Amount.setText("Enter Amount: " + RepeatCount + " loop(s)");
					SettingsMenu_RCount_Amount.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_RCount_Amount(e);
						}
					});
					SettingsMenu_RCount.add(SettingsMenu_RCount_Amount);

					//---- SettingsMenu_RCount_TimedAmount ----
					SettingsMenu_RCount_TimedAmount.setText("Enter Timed Amount: " + Double.valueOf(TimedRepeatLength/1000) + " second(s)");
					SettingsMenu_RCount_TimedAmount.setEnabled(false);
					SettingsMenu_RCount_TimedAmount.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_RCount_TimedAmount(e);
						}
					});
					SettingsMenu_RCount.add(SettingsMenu_RCount_TimedAmount);
				}
				SettingsMenu.add(SettingsMenu_RCount);

				//======== SettingsMenu_WPM ========
				{
					SettingsMenu_WPM.setText("Words Per Minute");

					//---- SettingsMenu_WPM_Randomizer ----
					SettingsMenu_WPM_Randomizer.setText("Randomize Words Per Minute");
					SettingsMenu_WPM_Randomizer.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_WPM_Randomizer(e);
						}
					});
					SettingsMenu_WPM.add(SettingsMenu_WPM_Randomizer);

					//---- SettingsMenu_WPM_Amount ----
					SettingsMenu_WPM_Amount.setText("Enter Amount: " + WordsPerMinute + " WPM");
					SettingsMenu_WPM_Amount.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_WPM_Amount(e);
						}
					});
					SettingsMenu_WPM.add(SettingsMenu_WPM_Amount);

					//---- SettingsMenu_WPM_RandomizeAmount ----
					SettingsMenu_WPM_RandomizeAmount.setText("Enter Randomize Amount: +/- " + RandomizedWordsPerMinute + " WPM");
					SettingsMenu_WPM_RandomizeAmount.setEnabled(false);
					SettingsMenu_WPM_RandomizeAmount.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_WPM_RandomizeAmount(e);
						}
					});
					SettingsMenu_WPM.add(SettingsMenu_WPM_RandomizeAmount);
				}
				SettingsMenu.add(SettingsMenu_WPM);

				//======== SettingsMenu_WaitTime ========
				{
					SettingsMenu_WaitTime.setText("Wait Time");

					//---- SettingsMenu_WaitTime_StartWhenLoseFocus ----
					SettingsMenu_WaitTime_StartWhenLoseFocus.setText("Start when window loses focus");
					SettingsMenu_WaitTime_StartWhenLoseFocus.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_WaitTime_StartWhenLoseFocus(e);
						}
					});
					SettingsMenu_WaitTime.add(SettingsMenu_WaitTime_StartWhenLoseFocus);

					//---- SettingsMenu_WaitTime_EndWhenGainFocus ----
					SettingsMenu_WaitTime_EndWhenGainFocus.setText("End when window gains focus");
					SettingsMenu_WaitTime_EndWhenGainFocus.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_WaitTime_EndWhenGainFocus(e);
						}
					});
					SettingsMenu_WaitTime.add(SettingsMenu_WaitTime_EndWhenGainFocus);

					//---- SettingsMenu_WaitTime_Amount ----
					SettingsMenu_WaitTime_Amount.setText("Enter Amount: " + Double.valueOf(WaitTime/1000) + " second(s)");
					SettingsMenu_WaitTime_Amount.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SettingsMenu_WaitTime_Amount(e);
						}
					});
					SettingsMenu_WaitTime.add(SettingsMenu_WaitTime_Amount);
				}
				SettingsMenu.add(SettingsMenu_WaitTime);
			}
			menuBar2.add(SettingsMenu);
		}
		setJMenuBar(menuBar2);

		//======== scrollPane1 ========
		{

			//---- TypingField ----
			TypingField.setText("Type here");
			TypingField.setForeground(new Color(150, 150, 150));
			TypingField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					TypingFieldFocusLost(e);
				}
				@Override
				public void focusGained(FocusEvent e) {
					TypingFieldFocusGained(e);
				}
			});
			scrollPane1.setViewportView(TypingField);
		}
		contentPane.add(scrollPane1, CC.xywh(2, 2, 2, 2));

		//---- TypeKey ----
		TypeKey.setText("Type");
		TypeKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TypeKey(e);
			}
		});
		contentPane.add(TypeKey, CC.xy(2, 4));

		//---- DesignerPlaque ----
		DesignerPlaque.setText("                                      Designed by Austin Seber     Version " + VERSION);
		DesignerPlaque.setVerticalAlignment(SwingConstants.TOP);
		DesignerPlaque.setHorizontalTextPosition(SwingConstants.LEFT);
		DesignerPlaque.setBackground(new Color(216, 213, 206));
		DesignerPlaque.setRequestFocusEnabled(false);
		DesignerPlaque.setPreferredSize(new Dimension(40, 19));
		DesignerPlaque.setOpaque(false);
		DesignerPlaque.setEnabled(false);
		contentPane.add(DesignerPlaque, CC.xy(3, 4));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	
	private void createDialog(String type,double d) {
		
		String string = (String)JOptionPane.showInputDialog(this,null,type,JOptionPane.PLAIN_MESSAGE,null,null,d/1000);
		
		if (string != null && string.length() > 0 && verifyString(type,string)) {
				
			double value = Double.valueOf(string);
			
			if (type.equals(WAIT_TIME)) {
				
				
				if (value < 0) {
					
					value = 0;
					
				}
				
				WaitTime = value*1000;
				SettingsMenu_WaitTime_Amount.setText("Enter Amount: " + Double.toString(WaitTime/1000) + " second(s)");
				return;
					
			} else if (type.equals(TIMED_REPEAT_LENGTH)) {
				
				if (value < 0) {
					
					value = 0;
					
				}
				
				TimedRepeatLength = value*1000;
				SettingsMenu_RCount_TimedAmount.setText("Enter Timed Amount: " + Double.toString(TimedRepeatLength/1000) + " second(s)");
				return;
				
			}
		
		}

		System.out.println("Could not set variable -- string was invalid.");
		
	}
	
	private void createDialog(String type, int i) {
		
		String string = (String)JOptionPane.showInputDialog(this,null,type,JOptionPane.PLAIN_MESSAGE,null,null,i);
		
		if (string != null && string.length() > 0 && verifyString(type,string)) {
				
			int value = Integer.valueOf(string);
			
			if (type.equals(REPEAT_COUNT)) {
				
				if (value < 0) {
					
					value = 0;
					
				}
				
				RepeatCount = value;
				SettingsMenu_RCount_Amount.setText("Enter Amount: " + RepeatCount + " loop(s)");
				return;
				
			} else if (type.equals(WORDS_PER_MINUTE)) {
				
				if (value > 1000) {
					
					value = 1000;
				
				}
				
				WordsPerMinute = value;
				SettingsMenu_WPM_Amount.setText("Enter Amount: " + WordsPerMinute + " WPM");
				return;
				
			} else if (type.equals(RANDOMIZE_WORDS_PER_MINUTE)) {
				
				if (value > 1000) {
					
					value = 1000;
				
				}
				
				RandomizedWordsPerMinute = value;
				SettingsMenu_WPM_RandomizeAmount.setText("Enter Randomize Amount: +/-" + RandomizedWordsPerMinute + " WPM");
				return;
				
			}
			
			System.out.println("Could not find set dialog value to the corresponding value.");
			
		}

		System.out.println("Could not set variable -- string was invalid.");
		
	}
	
	private static boolean verifyString(String type, String string) {
		
		if (type.equals(WAIT_TIME) || type.equals(TIMED_REPEAT_LENGTH)) {
		
			if (string.matches(".*[^0-9.]+.*")) {
		
				return false;
		
			}
		
		} else {
			
			if (string.matches(".*[^0-9]+.*")) {
				
				return false;
		
			}
			
		}

		return true;
		
	}
	
	public static void stopTyping() {
		
		typer.stopTyper();
		typer = null;
		running = false;
		TypeKey.setText("Type");
		TypingField.setEnabled(true);
		FileMenu.setEnabled(true);
		SettingsMenu.setEnabled(true);
		
	}
	
	public static void startTyping() {
		
		typer = new Typer();
		typer.startTyper(TypingField.getText(),UnlimitedRepeats,TimedRepeats,RepeatCount,WordsPerMinute,WaitTime,TimedRepeatLength);
		running = true;
		TypeKey.setText("Stop");
		TypingField.setEnabled(false);
		FileMenu.setEnabled(false);
		SettingsMenu.setEnabled(false);
		
	}
	
	private static void reset() {
		
		if (running) {
			
			stopTyping();
		
		}
		
		System.out.println("Resetting all params.");
		TypingField.setText(DEFAULT_TYPING_FIELD_TEXT);
		UnlimitedRepeats = false;
		TimedRepeats = false;
		RandomizeWordsPerMinute = false;
		StartOnLoseFocus = false;
		EndOnGainFocus = false;
		RepeatCount = 0;
		WordsPerMinute = 50;
		RandomizedWordsPerMinute = 0;
		WaitTime = 1000;
		TimedRepeatLength = 30000;
		
	}
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Austin Seber
	private static JMenuBar menuBar2;
	private static JMenu FileMenu;
	private static JMenuItem FileMenu_New;
	private static JMenuItem FileMenu_Close;
	private static JMenu SettingsMenu;
	private static JMenu SettingsMenu_RCount;
	private static JCheckBoxMenuItem SettingsMenu_RCount_UnlimitedRepeats;
	private static JCheckBoxMenuItem SettingsMenu_RCount_TimedRepeats;
	private static JMenuItem SettingsMenu_RCount_Amount;
	private static JMenuItem SettingsMenu_RCount_TimedAmount;
	private static JMenu SettingsMenu_WPM;
	private static JCheckBoxMenuItem SettingsMenu_WPM_Randomizer;
	private static JMenuItem SettingsMenu_WPM_Amount;
	private static JMenuItem SettingsMenu_WPM_RandomizeAmount;
	private static JMenu SettingsMenu_WaitTime;
	private static JCheckBoxMenuItem SettingsMenu_WaitTime_StartWhenLoseFocus;
	private static JCheckBoxMenuItem SettingsMenu_WaitTime_EndWhenGainFocus;
	private static JMenuItem SettingsMenu_WaitTime_Amount;
	private static JScrollPane scrollPane1;
	private static JTextArea TypingField;
	private static JButton TypeKey;
	private static JMenuItem DesignerPlaque;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

}
