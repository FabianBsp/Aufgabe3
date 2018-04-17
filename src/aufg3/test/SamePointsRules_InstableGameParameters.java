/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 2003 MHz, 16000 MByte RAM)
 **/
package aufg3.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import edu.hm.cs.rs.arch.a03_srp.UndercutMonoNew;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Test fuer ein Undercut-Programm.
 * 
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-03-30
 */
@RunWith(Parameterized.class)
public class SamePointsRules_InstableGameParameters {
	/** SUT type. */
	private static final Class<?> sutType = UndercutMonoNew.class;

	@Parameters(name = "{0} => {1}")
	public static Iterable<Object[]> generate() {
		return Arrays.asList(new Object[][] {
				// abwechselnd Wahl Spieler A/B, Regex in der Ausgabe
				{ "12", "Player A: 3, Player B: 0" }, { "15", "Player A: 1, Player B: 5" },
				{ "22", "Player A: 0, Player B: 0" }, { "13", "Player A: 1, Player B: 3" },
				{ "4545", "Player A: 18, Player B: 0" }, { "4553", "Player A: 14, Player B: 3" },
				{ "1323", "Player A: 6, Player B: 3" }, { "4545", "Player A: 18, Player B: 0" },
				{ "344334434334344334434334", "Player A: 35, Player B: 42.+Player B wins" },
				{ "344334434334344334434554", "Player A: 44, Player B: 35.+Player A wins" },
				{ "33333333", "Player A: 0, Player B: 0.+Tie" },
				{ "", "." }, // Stopper, ohne
																										// Funktion
		});
	}

	@Parameter(0)
	public String input;

	@Parameter(1)
	public String wantRegex;

	@Test(timeout = 1_000)
	public void playGame() throws ReflectiveOperationException, IOException {
		// arrange
		final Pattern wantPattern = Pattern.compile(wantRegex, Pattern.DOTALL);
		// act
		final String have = captureMainOutput(input);
		// assert
		assertTrue(wantPattern.matcher(have).find());
	}

	/**
	 * Ruft eine main-Methode auf mit einem String als Konsoleneingabe. Liefert die
	 * Konsolenausgabe als String zurueck.
	 * 
	 * @param input
	 *            String, dessen Zeichen die main-Methode von System.in liest.
	 * @return Was immer main aus Systemout geschrieben hat. System.err geht dran
	 *         vorbei.
	 * @throws ReflectiveOperationException
	 *             wenn main nicht funktioniert.
	 * @throws IOException
	 *             sollte nicht vorkommen:-)
	 */
	private static String captureMainOutput(String input) throws ReflectiveOperationException, IOException {
		final Method main = sutType.getDeclaredMethod("main", String[].class);
		final PrintStream systemOut = System.out;
		final InputStream systemIn = System.in;
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				PrintStream capturedOutput = new PrintStream(byteArrayOutputStream);
				ByteArrayInputStream fakeInput = new ByteArrayInputStream(input.getBytes())) {
			System.setOut(capturedOutput); // redirect console Dialog
			System.setIn(fakeInput);
			try {
				main.invoke(null, (Object) new String[0]);
			} catch (InvocationTargetException exception) {
				if (!(exception.getCause() instanceof IOException)) // expect and discard IOX on short input
					throw exception;
			}
			return byteArrayOutputStream.toString();
		} finally {
			System.setOut(systemOut); // restore console Dialog
			System.setIn(systemIn);
		}
	}

}