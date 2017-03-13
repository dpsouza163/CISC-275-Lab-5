package misc;

import java.util.TimerTask;

import main.ViewMain;



//Timer stuff
public class ExpireTask extends TimerTask {
	public ExpireTask(){
		
	}
	@Override
	public void run(){
		if (ViewMain.timeRemaining >= 0){
			ViewMain.timeRemaining--;
		}
		else {
			cancel();
		}
	}
}
	
	