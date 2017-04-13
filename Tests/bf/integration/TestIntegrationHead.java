package bf.integration;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.HeadApplication;

public class TestIntegrationHead {

	
	private final static String NEWLINE = System.lineSeparator();
	private final static String TESTMESSAGE = "test"+NEWLINE+"string";
	
	@Test
	public void testAllNullArgument() {
		ShellImpl shell = new ShellImpl();

		try{
			shell.parseAndEvaluate(null, null);
		}catch (Exception e){
			String actual=e.getMessage();
			assertEquals(null,actual);
			
	}
	}

	@Test
	public void testNullOutputStream() {
		ShellImpl shell = new ShellImpl();
		String testString = TESTMESSAGE;
		OutputStream stdout = null;
		try{
			shell.parseAndEvaluate(testString, stdout);
		}catch (Exception e){
			String actual=e.getMessage();
			assertEquals("shell: test: Invalid app.",actual);
			
	}
	}
	@Test
	public void testNoArgument() throws ShellException{
		ShellImpl shell = new ShellImpl();
		OutputStream stdout = new ByteArrayOutputStream();
		//No args with InputStream and OutputStream
			try {
				shell.parseAndEvaluate("", stdout);
			} catch (Exception e) {
				String actual=e.getMessage();
				assertEquals("shell: : Invalid app.",actual);
			}
	}
	@Test
	public void testOneArgument() throws ShellException{
		ShellImpl shell = new ShellImpl();
		OutputStream stdout = new ByteArrayOutputStream();
		stdout = new ByteArrayOutputStream();
		try {
			shell.parseAndEvaluate("head Tests/headFiles/123.txt", stdout);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String testStr = "31423" + NEWLINE + "1" + NEWLINE + "15ew"+ NEWLINE + "afg" + NEWLINE + "gaqwtq345" + NEWLINE +"tqtqt" + NEWLINE + "c592859v" +NEWLINE +"gasgsad" +NEWLINE;
		assertEquals( testStr,stdout.toString());
	}
	@Test
	public void testThreeArgument() throws ShellException{
		ShellImpl shell = new ShellImpl();
		OutputStream stdout = new ByteArrayOutputStream();
		String testStr = "31423" + NEWLINE + "1" + NEWLINE;

		try {
			shell.parseAndEvaluate("head -n 2 Tests/headFiles/123.txt", stdout);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals( testStr,stdout.toString());
	}

	@Test
	public void testThreeArgumentTwo() throws ShellException{
		ShellImpl shell = new ShellImpl();
		OutputStream stdout = new ByteArrayOutputStream();
		String testStr = "31423" + NEWLINE + "1" + NEWLINE;

		try {
			shell.parseAndEvaluate("head Tests/headFiles/123.txt -n 2", stdout);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals( testStr,stdout.toString());
	}
	@Test
	public void testInvalidThreeArgument() throws ShellException{
		ShellImpl shell = new ShellImpl();
		OutputStream stdout = new ByteArrayOutputStream();
		String testStr = "31423" + NEWLINE + "1" + NEWLINE;

		try {
			shell.parseAndEvaluate("head -n Tests/headFiles/123.txt 2", stdout);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			String actual=e.getMessage();
			assertEquals("Head: Invalid Command Format"+NEWLINE+"Usage: head [-n lines] [file]",actual);
		}
		
	}
}
