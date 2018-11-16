package noxcuse.logica;

public class Datos {
	public static String isNull(String data) {
		if(data == null)
			return "NULL";
		return data;
	}
	
	public static String toSQL(String data) {
		if(data==null)
			return "NULL";
		return "'" + data + "'";
	}
	
	public static String setIntOrNull(int data) {
		if (data != 0) {
	       return String.valueOf(data);
	    } else {
	        return "NULL";
	    }
	}
}
