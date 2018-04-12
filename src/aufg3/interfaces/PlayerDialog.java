package aufg3.interfaces;

import java.io.IOException;

public interface PlayerDialog {
	int[] input(int[]... choices);
	void output(int[] playerpoints);
	void close() throws IOException;
}
