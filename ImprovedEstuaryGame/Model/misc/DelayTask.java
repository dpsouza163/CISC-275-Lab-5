package misc;

import java.util.TimerTask;

import main.Controller;
import main.ViewMain;



//Timer stuff
public class DelayTask extends TimerTask {
	public DelayTask(){
		Controller.delay = 2;

	}
	@Override
	public void run(){
		if (Controller.delay > 0){
			Controller.delay--;
		}
		else {

			cancel();
			Controller.allButtons.remove(Controller.xButton);
			Controller.allButtons.remove(Controller.checkButton);
			Controller.checkOrXDisplayed = false;

		}

	}
}

