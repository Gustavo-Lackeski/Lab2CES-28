import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CalculadoraString {
	private static ArrayList<Integer> NumerosNegativos;
	private static String _numbers;
	public static int add(String numbers) throws Exception{
		NumerosNegativos = new ArrayList<Integer>();
		int resultado = 0;
		String regex = "";
		//regex: string que definira quais os marcadores para split
		regex = calcRegex(numbers);	
		String[] tokens;
		//da split definido por regex
		tokens =  _numbers.split(regex);
		for(String s: tokens){	
			resultado = resultado + stringValor(s);
		}
		//se nao ha numeros negativos, retorna o resultado
		if (NumerosNegativos.isEmpty())
			return resultado;
		else
			//caso contrario, lança-se NegativeNumbersException
			throw new NegativeNumbersException(NumerosNegativos.toString());
	}
	//retorna o valor da String(0 se for nula ou com espacos brancos ou maior que 1000) e verifica se é negativa
	public static int stringValor(String s){
		if(isNumeric(s)){
			//adiciona numeros negativos a NumerosNegativos
			if (Integer.parseInt(s) < 0)
				NumerosNegativos.add(Integer.parseInt(s));
			//se > 1000 é zero
			if(Integer.parseInt(s) >1000)
				return 0;
			return Integer.parseInt(s);
			
		}
		else{
			if(!isWhiteSpaces(s) && !s.isEmpty()){
				throw new IllegalArgumentException(s);
			}
			return 0;
		}		
	}
	//calcula o regex da string
	public static String calcRegex(String s){
		String resultado;
		if (s.startsWith("//")){
			if (s.indexOf("\n") < 0){
				throw new IllegalArgumentException(s);
			}
			String temp = s.substring(2, s.indexOf("\n"));
			_numbers = s.substring(s.indexOf("\n") + 1);
			Matcher matcher = Pattern.compile("\\[([^\\]]+)").matcher(temp);
			int pos = -1;
			resultado = "";
			while(matcher.find(pos+1)){
				pos = matcher.start();
				resultado = resultado +matcher.group(1) + "|";
			}
			return "["+ resultado + ",|\\s+" + "]";
		}
		else{
			_numbers = s;
			return "["+ ",|\\s+" + "]";
		}
	}
	//verifica se s é um numero
	public static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	//verifica se a string so tem espacos brancos
	public static boolean isWhiteSpaces(String s){
		return s != null && s.matches("\\s+");	 
	}	
	public static void main(String[] args) {
		
		
		
		// testes carteados, ignorar e apagar antes de enviar!!!!!!!!!!
		
		
		
		
		
	/*	String str = "\\123\n456";
		String substr = "\n";
		String before = str.substring(1, str.indexOf(substr));
		String after = str.substring(str.indexOf(substr) + substr.length());
		System.out.println(before);
		System.out.println(after);
		String s;
		s = "\\blablabla";
		if(s.startsWith("\\"))
			System.out.println("\\");*/
	}

}
// |||