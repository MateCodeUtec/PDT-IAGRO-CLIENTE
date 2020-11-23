package helpers;

import enums.TipoDato;

public class DevuelveTipoDato {

	public static String devuelveString (String tp) {
		
			switch (tp) {
			case "STRING": {
				
				return "CADENA DE TEXTO";
			}
			case "INT": {
				
				return "NUMERO ENTERO";
			}
			case "BOOLEAN": {
	
				return "VERDADERO O FALSO";
			}
			case "FLOAT": {
				
				return "NUMERO CON DECIMALES";
			}
			case "DOUBLE": {
				
				return "NUMERO CON DECIMALES";
			}
			case "DATE": {
				
				return "FECHA";
			}
			case "DATETIME": {
				
				return "FECHA y HORA";
			}
			case "BLOB": {
				
				return "ARCHIVO";
			}
			default:
				return tp;
			}
		
	}
	
	public static TipoDato devuelveTipoDato (String tp) {
		
		switch (tp) {
		case "CADENA DE TEXTO": {
			
			return TipoDato.STRING;
		}
		case "NUMERO ENTERO": {
			
			return TipoDato.INT ;
		}
		case "VERDADERO O FALSO": {

			return TipoDato.BOOLEAN;
		}
		case "NUMERO CON DECIMALES": {
			
			return TipoDato.FLOAT;
		}
		case "NUMERO GRANDE CON DECIMALES": {
			
			return TipoDato.DOUBLE ;
		}
		case "FECHA": {
			
			return TipoDato.DATE;
		}
		case "FECHA y HORA": {
			
			return TipoDato.DATETIME;
		}
		case "ARCHIVO": {
			
			return TipoDato.BLOB;
		}
		default:
			return TipoDato.STRING;
		}
	
}
	

}
