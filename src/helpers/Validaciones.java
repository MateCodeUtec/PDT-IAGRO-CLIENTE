package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
	/**
	 * Valida si el campo esta vacio
	 * @param txt
	 * @return boolean 
	 */
	public static boolean esVacio(String txt) {
		return txt.isEmpty() || txt.isBlank();
	}
	/**
	 * Valida si es letra o un espacio
	 * @param c
	 * @return boolean
	 */
	public static boolean esLetraOEspacio(char c) {
		return Character.isLetter(c) || Character.isWhitespace(c);
	}
	
	/**
	 * Valida si es una letra
	 * @param c
	 * @return boolean
	 */
	public static boolean esLetra(char c) {
		return Character.isLetter(c);
	}
	
	/**
	 * Valida si la contraseña es correcta
	 * @param pass
	 * @return boolean 
	 */
	public static boolean password(String pass) {
		
		var letra = false;
		var numero = false;
		
		for (int i = 0; i < pass.length(); i++) {
			if(!Character.isLetterOrDigit(pass.charAt(i))) {
				return false;
			}
			
			if(Character.isLetter(pass.charAt(i))) {
				letra = true;
			}
			
			if(Character.isDigit(pass.charAt(i))) {
				numero = true;
			}
		}
	
		return letra && numero && pass.length() >= 8;
	}
	
	
	
	/**
	 * Valida si el formato del correo es correcto
	 * @param email
	 * @return boolean
	 */
    public static boolean esCorreo(String email) 
    { 
    	 Pattern VALID_EMAIL_ADDRESS_REGEX = 
    			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    	 
    	 Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    	 return matcher.find();
    }
}
