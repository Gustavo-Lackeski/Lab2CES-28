import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;




//NAO TESTEI ENTRADAS INVALIDAS DO 4 PARA FRENTE




public class calculadorateste {
	CalculadoraString calc;
	String bla;
	
	@Before
	public void setup(){
		calc = new CalculadoraString();
	}
	@Test
	public void isNumerictest(){
		assertTrue(CalculadoraString.isNumeric("14"));
		assertTrue(CalculadoraString.isNumeric("31"));
		assertTrue(CalculadoraString.isNumeric("1"));
		assertTrue(CalculadoraString.isNumeric("1453"));
		assertFalse(CalculadoraString.isNumeric("1x4"));
		assertFalse(CalculadoraString.isNumeric("x"));
		assertFalse(CalculadoraString.isNumeric("144 s"));
	}
	@Test
	public void testcalcRegex(){
		String s = CalculadoraString.calcRegex("//[;]\n1;2,3\n4");
		assertTrue(s.equals("[;|,|\\s+]"));
		s = CalculadoraString.calcRegex("//[;][!]\n1;2,3\n4");
		assertTrue(s.equals("[;|!|,|\\s+]"));
	}
	@Test
	//caso vazio
	public void stringVazia() throws Exception {
		assertEquals(CalculadoraString.add(""),0);
	}
	//entradas validas do caso 1
	@Test
	public void testStringCaso1Validas() throws Exception{
		assertEquals(CalculadoraString.add("1,2,3"),6);
		assertEquals(CalculadoraString.add("1,2,30"),33);
		assertEquals(CalculadoraString.add("1,,,2"),3);
		assertEquals(CalculadoraString.add("1,4,5"),10);
		assertEquals(CalculadoraString.add("1,2 3"),6);
	}
	//entradas invalidas do caso 1
	@Rule
	public ExpectedException expectedEx2 = ExpectedException.none();
	@Test
	public void testStringCaso1NaoValidas() throws Exception{
		expectedEx2.expect(IllegalArgumentException.class);
		CalculadoraString.add("1,3,x");			
	}
	//entradas validas caso 2
	@Test
	public void testStringCaso2Validas() throws Exception{
		assertEquals(CalculadoraString.add(" "),0);
		assertEquals(CalculadoraString.add(","),0);
		assertEquals(CalculadoraString.add("1,,"),1);
		assertEquals(CalculadoraString.add(", , ,,"),0);
		assertEquals(CalculadoraString.add("1,2,3,4,50"),60);
		
	}
	//entradas invalidas do caso 2
	@Test(expected = IllegalArgumentException.class)
	public void testStringCaso2NaoValidas() throws Exception{
		CalculadoraString.add("1,3,X,41,3,2");
		assertEquals(CalculadoraString.add("1,,"),2);		
	}
	@Test
	public void testStringCaso3Validas() throws Exception{
		assertEquals(CalculadoraString.add("1\n2,3"),6);
		assertEquals(CalculadoraString.add("1\n"),1);
		assertEquals(CalculadoraString.add("1\n\n2,3 4"),10);

	}
	//entradas invalidas do caso 3
	@Test(expected = IllegalArgumentException.class)
	public void testStringCaso3NaoValidas() throws Exception{
			CalculadoraString.add("132,a,X,41,\n\n2");
	}
	//caso 4
	@Test
	public void testStringCaso4() throws Exception{
		assertEquals(CalculadoraString.add("//[;]\n1;2"),3);
		assertEquals(CalculadoraString.add("//[a]\n1a2,3"),6);
		assertEquals(CalculadoraString.add("//[)]\n1)2,3"),6);
		assertEquals(CalculadoraString.add("//[*]\n1*2,3 10"),16);		
	}
		
	//caso 5
	@Rule
	public ExpectedException expectedEx3 = ExpectedException.none();
	@Test
	public void testStringCaso5Negativos() throws Exception{
		expectedEx3.expect(NegativeNumbersException.class);
		expectedEx3.expectMessage("negativos nao permitidos:[-2]");
		CalculadoraString.add("1,-2,3");
	}
	@Rule
	public ExpectedException expectedEx4 = ExpectedException.none();
	@Test
	public void testStringCaso5Negativos2() throws Exception{
		expectedEx3.expect(NegativeNumbersException.class);
		expectedEx3.expectMessage("negativos nao permitidos:[-2, -3]");
		CalculadoraString.add("1,-2,-3");
	}
	//caso 6
	@Test
	public void testStringCaso6() throws Exception{	
		assertEquals(CalculadoraString.add("1001"),0);
		assertEquals(CalculadoraString.add("1,2,3000"),3);
		assertEquals(CalculadoraString.add("//[;]\n1;2,2000"),3);
		assertEquals(CalculadoraString.add("//[*]\n1*2,3 10*1001"),16);		
	}
	//caso 7
	@Test
	public void testStringCaso7() throws Exception{
		assertEquals(CalculadoraString.add("//[;;;]\n1;;;2"),3);
		assertEquals(CalculadoraString.add("//[aa]\n1aa2,3"),6);
		assertEquals(CalculadoraString.add("//[))]\n1))2,3,4"),10);
		assertEquals(CalculadoraString.add("//[***]\n1***2,3 10"),16);		
	}
	//caso 8
	@Test
	public void testStringCaso8() throws Exception{
		assertEquals(CalculadoraString.add("//[;][a]\n1;2a10"),13);
		assertEquals(CalculadoraString.add("//[a][b]\n1ab2,3b10"),16);
		assertEquals(CalculadoraString.add("//[)][a][*]\n1)2,3*4a10"),20);
		assertEquals(CalculadoraString.add("//[*][;][!][k]\n1k2,3 10k10;4!5*6"),41);		
	}
	//caso 9
	@Test
	public void testStringCaso9() throws Exception{
		assertEquals(CalculadoraString.add("//[;;;][a]\n1;;;2a10"),13);
		assertEquals(CalculadoraString.add("//[aaa][bb]\n1aaabb2,3bb10"),16);
		assertEquals(CalculadoraString.add("//[))][aa][***]\n1))2,3***4aa10"),20);
		assertEquals(CalculadoraString.add("//[**][;][!!!][kk]\n1kk2,3 10kk10;4!!!5**6"),41);		
	}
	

}
