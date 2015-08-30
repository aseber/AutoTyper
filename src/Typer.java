import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Typer extends Thread {

	public void run() {
		
		System.out.println("Created new typing thread.");
		
		while (stopTyper == false) {
		
			if (WaitTime > 0 && FinishedWaitTime == false) {
			
				System.out.println("Sleeping Typer thread for " + Double.toString(WaitTime/1000) + " second(s).");
				
				synchronized (this) {
		
					try {

						this.sleep((long)WaitTime);

					} catch (InterruptedException e) {

						break;
							
					}
			
				}
				
				FinishedWaitTime = true;
			
			}

			if (repeats == 0) {
				
				System.out.println("Typer thread has started executing.");
				
			}
			
			for (index = 0; index < Text.length(); index++) {

				typeKey(Text.charAt(index));
				
				synchronized (this) {
					
					try {
				
						this.sleep(10000/WordsPerMinute);
				
					} catch (InterruptedException e) {

						break;
				
					}
					
				}
				
			}
				
			if (UnlimitedRepeats == false && TimedRepeats == false) {
					
				if (repeats == RepeatCount) {
				
					MainWindow.stopTyping();
					break;
					
				}
			
			}
		
			if (TimedRepeats == true) {
				
				if (StartTime + TimedRepeatLength < System.currentTimeMillis()) {
					
					MainWindow.stopTyping();
					break;
					
				}
				
			}
			
			repeats++;
			
		}
		
		System.out.println("Stopped execution of Typer Thread.");
		
	}
	
	public void startTyper(String s,Boolean b,boolean b2,int i,int i2,double d,double d2) {

		Text = s;
		UnlimitedRepeats = b;
		TimedRepeats = b2;
		RepeatCount = i;
		WordsPerMinute = i2;
		WaitTime = d;
		TimedRepeatLength = d2;
		
		this.start();
		
		try {

			robot = new Robot(); 

		} catch (AWTException e) {
			
			System.out.println("Could not create keyboard emulator. Closing Typer Thread.");
			MainWindow.stopTyping();
			e.printStackTrace();
			
		}
		
	}
	
	public void stopTyper() {
		
		this.interrupt();
		stopTyper = true;
		
	}
	
	private void typeKey(char c) {
		
		switch (c) {

		case 'a':
			robot.keyPress(KeyEvent.VK_A);
			break;
			
		case 'b':
			robot.keyPress(KeyEvent.VK_B);
			break;
			
		case 'c':
			robot.keyPress(KeyEvent.VK_C);
			break;
			
		case 'd':
			robot.keyPress(KeyEvent.VK_D);
			break;
			
		case 'e':
			robot.keyPress(KeyEvent.VK_E);
			break;
			
		case 'f':
			robot.keyPress(KeyEvent.VK_F);
			break;
			
		case 'g':
			robot.keyPress(KeyEvent.VK_G);
			break;
			
		case 'h':
			robot.keyPress(KeyEvent.VK_H);
			break;
			
		case 'i':
			robot.keyPress(KeyEvent.VK_I);
			break;
			
		case 'j':
			robot.keyPress(KeyEvent.VK_J);
			break;
			
		case 'k':
			robot.keyPress(KeyEvent.VK_K);
			break;
			
		case 'l':
			robot.keyPress(KeyEvent.VK_L);
			break;
			
		case 'm':
			robot.keyPress(KeyEvent.VK_M);
			break;
			
		case 'n':
			robot.keyPress(KeyEvent.VK_N);
			break;
			
		case 'o':
			robot.keyPress(KeyEvent.VK_O);
			break;
			
		case 'p':
			robot.keyPress(KeyEvent.VK_P);
			break;
			
		case 'q':
			robot.keyPress(KeyEvent.VK_Q);
			break;
			
		case 'r':
			robot.keyPress(KeyEvent.VK_R);
			break;
			
		case 's':
			robot.keyPress(KeyEvent.VK_S);
			break;
			
		case 't':
			robot.keyPress(KeyEvent.VK_T);
			break;
			
		case 'u':
			robot.keyPress(KeyEvent.VK_U);
			break;
			
		case 'v':
			robot.keyPress(KeyEvent.VK_V);
			break;
			
		case 'w':
			robot.keyPress(KeyEvent.VK_W);
			break;
			
		case 'x':
			robot.keyPress(KeyEvent.VK_X);
			break;
			
		case 'y':
			robot.keyPress(KeyEvent.VK_Y);
			break;
			
		case 'z':
			robot.keyPress(KeyEvent.VK_Z);
			break;
			
		case 'A':
			typeWithShift(KeyEvent.VK_A);
			break;
			
		case 'B':
			typeWithShift(KeyEvent.VK_B);
			break;
			
		case 'C':
			typeWithShift(KeyEvent.VK_C);
			break;
			
		case 'D':
			typeWithShift(KeyEvent.VK_D);
			break;
			
		case 'E':
			typeWithShift(KeyEvent.VK_E);
			break;
			
		case 'F':
			typeWithShift(KeyEvent.VK_F);
			break;
			
		case 'G':
			typeWithShift(KeyEvent.VK_G);
			break;
			
		case 'H':
			typeWithShift(KeyEvent.VK_H);
			break;
			
		case 'I':
			typeWithShift(KeyEvent.VK_I);
			break;
			
		case 'J':
			typeWithShift(KeyEvent.VK_J);
			break;
			
		case 'K':
			typeWithShift(KeyEvent.VK_K);
			break;
			
		case 'L':
			typeWithShift(KeyEvent.VK_L);
			break;
			
		case 'M':
			typeWithShift(KeyEvent.VK_M);
			break;
			
		case 'N':
			typeWithShift(KeyEvent.VK_N);
			break;
			
		case 'O':
			typeWithShift(KeyEvent.VK_O);
			break;
			
		case 'P':
			typeWithShift(KeyEvent.VK_P);
			break;
			
		case 'Q':
			typeWithShift(KeyEvent.VK_Q);
			break;
			
		case 'R':
			typeWithShift(KeyEvent.VK_R);
			break;
			
		case 'S':
			typeWithShift(KeyEvent.VK_S);
			break;
			
		case 'T':
			typeWithShift(KeyEvent.VK_T);
			break;
			
		case 'U':
			typeWithShift(KeyEvent.VK_U);
			break;
			
		case 'V':
			typeWithShift(KeyEvent.VK_V);
			break;
			
		case 'W':
			typeWithShift(KeyEvent.VK_W);
			break;
			
		case 'X':
			typeWithShift(KeyEvent.VK_X);
			break;
			
		case 'Y':
			typeWithShift(KeyEvent.VK_Y);
			break;
			
		case 'Z':
			typeWithShift(KeyEvent.VK_Z);
			break;
			
		case ' ':
			robot.keyPress(KeyEvent.VK_SPACE);
			break;
			
		case KeyEvent.VK_TAB:
			robot.keyPress(KeyEvent.VK_TAB);
			break;
			
		case KeyEvent.VK_ENTER:
			robot.keyPress(KeyEvent.VK_ENTER);
			break;
			
		case '1':
			robot.keyPress(KeyEvent.VK_1);
			break;
			
		case '2':
			robot.keyPress(KeyEvent.VK_2);
			break;
			
		case '3':
			robot.keyPress(KeyEvent.VK_3);
			break;
			
		case '4':
			robot.keyPress(KeyEvent.VK_4);
			break;
			
		case '5':
			robot.keyPress(KeyEvent.VK_5);
			break;
			
		case '6':
			robot.keyPress(KeyEvent.VK_6);
			break;
			
		case '7':
			robot.keyPress(KeyEvent.VK_7);
			break;
			
		case '8':
			robot.keyPress(KeyEvent.VK_8);
			break;
			
		case '9':
			robot.keyPress(KeyEvent.VK_9);
			break;
			
		case '0':
			robot.keyPress(KeyEvent.VK_0);
			break;
			
		case '!':
			typeWithShift(KeyEvent.VK_1);
			break;
			
		case '@':
			typeWithShift(KeyEvent.VK_2);
			break;
			
		case '#':
			typeWithShift(KeyEvent.VK_3);
			break;
			
		case '$':
			typeWithShift(KeyEvent.VK_4);
			break;
			
		case '%':
			typeWithShift(KeyEvent.VK_5);
			break;
			
		case '^':
			typeWithShift(KeyEvent.VK_6);
			break;
			
		case '&':
			typeWithShift(KeyEvent.VK_7);
			break;
			
		case '*':
			typeWithShift(KeyEvent.VK_8);
			break;
			
		case '(':
			typeWithShift(KeyEvent.VK_9);
			break;
			
		case ')':
			typeWithShift(KeyEvent.VK_0);
			break;
			
		case '/':
			robot.keyPress(KeyEvent.VK_SLASH);
			break;
			
		case '?':
			typeWithShift(KeyEvent.VK_SLASH);
			break;
			
		case KeyEvent.VK_BACK_SLASH:
			robot.keyPress(KeyEvent.VK_BACK_SLASH);
			break;
			
		case '=':
			robot.keyPress(KeyEvent.VK_EQUALS);
			break;
			
		case '+':
			typeWithShift(KeyEvent.VK_EQUALS);
			break;
			
		case '-':
			robot.keyPress(KeyEvent.VK_MINUS);
			break;
			
		case '_':
			typeWithShift(KeyEvent.VK_MINUS);
			break;
			
		case ',':
			robot.keyPress(KeyEvent.VK_COMMA);
			break;
			
		case '<':
			typeWithShift(KeyEvent.VK_COMMA);
			break;
			
		case '.':
			robot.keyPress(KeyEvent.VK_PERIOD);
			break;
			
		case '>':
			typeWithShift(KeyEvent.VK_PERIOD);
			break;
			
		case ';':
			robot.keyPress(KeyEvent.VK_SEMICOLON);
			break;
			
		case ':':
			typeWithShift(KeyEvent.VK_SEMICOLON);
			break;
			
		case KeyEvent.VK_QUOTE:
			robot.keyPress(KeyEvent.VK_QUOTE);
			break;
			
		case '"':
			typeWithShift(KeyEvent.VK_QUOTE);
			break;
			
		default:
			System.out.println("Could not emulate '" + c + "' key, add switch handlers for it.");
			break;
			
		}
		
	}

	private void typeWithShift(int i) {
		
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(i);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		
	}
	
	private Robot robot;
	private String Text;
	private Boolean UnlimitedRepeats;
	private Boolean TimedRepeats;
	private Boolean stopTyper = false;
	private Boolean FinishedWaitTime = false;
	private int RepeatCount;
	private int WordsPerMinute;
	private int repeats = 0;
	private int index = 0;
	private double WaitTime;
	private double TimedRepeatLength;
	private double StartTime = System.currentTimeMillis();

}
