package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Dato {
	public String nombre;
	public String email;
	public String empresa;
	public String producto;
	public String telefono;
	
	public Dato(String nombre, String email, String empresa, String producto, String telefono) {
		this.nombre = nombre;
		this.email = email;
		this.empresa = empresa;
		this.producto = producto;
		this.telefono = telefono;
	}
	
	public static ArrayList<Dato> leer(){
		ArrayList<Dato> lista = new ArrayList<Dato>();
		//loop for printing the array as phoneNumber is stored as array.
		Object ob;
		try {
			ob = new JSONParser().parse(new FileReader("datos.json"));
			JSONObject js = (JSONObject) ob;
	        JSONArray datos = (JSONArray) js.get("datos");
	        for (int i = 0; i < datos.size(); i++) {
	        	JSONObject d = (JSONObject) datos.get(i);
	        	Dato nuevoDato = new Dato((String)d.get("nombre"), (String)d.get("email"), (String)d.get("empresa"), (String)d.get("producto"),(String)d.get("telefono"));
	        	lista.add(nuevoDato);
	        }
	        
	        
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
            
		return lista;
	}
}
