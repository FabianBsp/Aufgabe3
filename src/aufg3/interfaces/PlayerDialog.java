package aufg3.interfaces;

import java.io.IOException;

public interface PlayerDialog {
	int input(int playertyp,int maxchoice,int minchoice,int[]... choices);
	void output(int roundplayed,int[] playerpoints);
	void close() throws IOException;
}
