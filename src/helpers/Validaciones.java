package helpers;

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
	/*
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
		
		for (int i = 0; i < pass.length(); i++) {
			if(!Character.isLetterOrDigit(pass.charAt(i))) {
				return false;
			}
		}
	
		return pass.length() >= 8;
	}
	
	/**
	 * Valida si el formato del correo es correcto
	 * @param email
	 * @return boolean
	 */
   public static boolean esCorreo(String email) 
   { 
       String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                           "[a-zA-Z0-9_+&-]+)@" + 
                           "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                           "A-Z]{2,7}$"; 
                             
       Pattern pat = Pattern.compile(emailRegex); 
       if (email == null) 
           return false; 
       return pat.matcher(email).matches(); 
   }
}
