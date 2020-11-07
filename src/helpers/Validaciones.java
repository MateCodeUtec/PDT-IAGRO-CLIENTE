package helpers;

public class Validaciones {
	/**
	 * Valida si el campo esta vacio
	 * @param txt
	 * @return boolean 
	 */
	public static boolean esVacio(String txt) {
		return txt.isEmpty() || txt.isBlank();
	}
}
