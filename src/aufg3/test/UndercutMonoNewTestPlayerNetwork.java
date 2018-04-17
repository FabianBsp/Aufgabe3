package aufg3.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.junit.jupiter.api.Test;

import edu.hm.cs.rs.arch.a03_srp.UndercutMonoNew;
import junit.framework.Assert;

class UndercutMonoNewTestPlayerNetwork {

	@Test
	void test() {
		
		try {
			String[] args = new String[] {"",""};
			UndercutMonoNew.main(args);
			
			Socket clienta = new Socket("localhost", 2001);
			Socket clientb = new Socket("localhost", 2002);
			try(BufferedReader in1 = new BufferedReader(new InputStreamReader(clienta.getInputStream()));
				BufferedReader in2 = new BufferedReader(new InputStreamReader(clientb.getInputStream()));
				OutputStreamWriter out1 = new OutputStreamWriter(clienta.getOutputStream());
				OutputStreamWriter out2 =  new OutputStreamWriter(clientb.getOutputStream());){
				
				String string = in1.readLine();
				//System.out.println(in1.readLine());
				//System.out.println(in1.readLine());
				out1.write("2");
				out1.flush();
				out2.write("3");
				out2.flush(); 
				//assertEquals("Undercut start",string);
				assertTrue(string.matches("Undercut start"));
				//assertEquals("Undercut start",in1.readLine());
				System.out.println("Hallo");
				System.out.println(in2.readLine());
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
