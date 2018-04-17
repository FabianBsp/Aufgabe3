package aufg3.interfaces;

import java.io.IOException;

public interface PlayerDialog {
	int input(int playertyp,int[]... choices) throws IOException;
	void output(int roundplayed,int[] playerpoints);
	void close() throws IOException;
	int getMinChoice(int[] choices);
	int getMaxChoice(int[] choices);
	boolean checkinput(int choice,int[] choices);
	void messageEnding(int playerwinner);
}
