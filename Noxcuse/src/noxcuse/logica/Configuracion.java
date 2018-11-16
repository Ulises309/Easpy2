package noxcuse.logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configuracion {
	private String host;
	private String bd;
	private String user;
	private String pwd;
	private String pathBD;
	private String pathWS;
	private String pathHTML;
	private String pathLayout;
	private String auditoria;
	private String ruta = "easpy.config";
	private String rutaJSON = "config.json";
	private String geneSys = "GeneSys";
	File f;
	private Vector<Perfil> perfiles;
	JSONParser parser = new JSONParser();

	public Configuracion() {
		perfiles = new Vector<Perfil>();
		f = new File(rutaJSON);
		if(!f.exists()) {
			JSONObject obj = new JSONObject();
			JSONArray perfiles = new JSONArray();
			obj.put("perfiles", perfiles);
			try (FileWriter file = new FileWriter(rutaJSON)) {
				file.write(obj.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		refreshPerfil();
		
		f = new File(ruta);
		Properties prop = new Properties();
		FileOutputStream os = null;
		FileInputStream input = null;
		if(!f.exists()) { 
			try {
				os = new FileOutputStream(f);
				prop.setProperty("host", "");
				prop.setProperty("bd", "");
				prop.setProperty("user", "");
				prop.setProperty("pwd", "");
				prop.setProperty("pathBD", "C:/BDs");
				prop.setProperty("pathWS", "C:/WSs");
				prop.setProperty("pathHTML", "C:/HTMLs");
				prop.setProperty("pathLayout", "");
				prop.setProperty("auditoria", "");
				prop.setProperty("geneSys", "geneSys");
				prop.store(os, null);
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			input = new FileInputStream(f);
			prop.load(input);
			this.host = prop.getProperty("host");
			this.bd = prop.getProperty("bd");
			this.user = prop.getProperty("user");
			this.pwd = prop.getProperty("pwd");
			this.pathBD = prop.getProperty("pathBD");
			this.pathWS = prop.getProperty("pathWS");
			this.pathHTML = prop.getProperty("pathHTML");
			this.pathLayout = prop.getProperty("pathLayout");
			this.auditoria = prop.getProperty("auditoria");
			this.auditoria = prop.getProperty("geneSys");
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeData(Properties p, String key, String value) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(f);
			p.setProperty(key, value);
			p.store(os, "Configuracion");
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Properties getProperties() {
		Properties prop = new Properties();
		FileInputStream input = null;
		try {
			input = new FileInputStream(f);
			prop.load(input);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
		Properties p = getProperties();
		writeData(p, "host", host);
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
		Properties p = getProperties();
		writeData(p, "bd", bd);
	}
	
	public String getGeneSys() {
		return geneSys;
	}

	public void setGeneSys(String geneSys) {
		this.geneSys = geneSys;
		Properties p = getProperties();
		writeData(p, "geneSys", geneSys);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		Properties p = getProperties();
		writeData(p, "user", user);
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
		Properties p = getProperties();
		writeData(p, "pwd", pwd);
	}

	public String getPathBD() {
		return pathBD;
	}

	public void setPathBD(String pathBD) {
		this.pathBD = pathBD;
		Properties p = getProperties();
		writeData(p, "pathBD", pathBD);
	}

	public String getPathWS() {
		return pathWS;
	}

	public void setPathWS(String pathWS) {
		this.pathWS = pathWS;
		Properties p = getProperties();
		writeData(p, "pathWS", pathWS);
	}
	
	
	public String getPathHTML() {
		return pathHTML;
	}

	public void setPathHTML(String pathHTML) {
		this.pathHTML = pathHTML;
		Properties p = getProperties();
		writeData(p, "pathHTML", pathHTML);
	}

	public String getPathLayout() {
		return pathLayout;
	}

	public void setPathLayout(String pathLayout) {
		this.pathLayout = pathLayout;
		Properties p = getProperties();
		writeData(p, "pathLayout", pathLayout);
	}

	public String getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(String auditoria) {
		this.auditoria = auditoria;
		Properties p = getProperties();
		writeData(p, "auditoria", auditoria);
	}

	public Perfil getPerfil(String nombre) {
		Perfil p = null;
		for(int i = 0; i < perfiles.size(); i++) {
			if(perfiles.get(i).getNombre().equals(nombre)) {
				p = perfiles.get(i);
				break;
			}
		}
		return p;
	}
	
	public Vector<Perfil> getPerfiles(){
		return this.perfiles;
	}
	
	public void addPerfil(Perfil p) {
		this.perfiles.add(p);
		guardarPerfiles();
	}
	
	public void removePerfil(Perfil p) {
		this.perfiles.remove(p);
		guardarPerfiles();
	}
	
	public void guardarPerfiles() {
		JSONObject obj = new JSONObject();
		JSONArray lista = new JSONArray();
		for(int i = 0; i < this.perfiles.size(); i++) {
			JSONObject perfil = new JSONObject();
			perfil.put("nombre", this.perfiles.get(i).getNombre());
			perfil.put("host", this.perfiles.get(i).getHost());
			perfil.put("bd", this.perfiles.get(i).getBd());
			perfil.put("user", this.perfiles.get(i).getUser());
			perfil.put("pwd", this.perfiles.get(i).getPwd());
			perfil.put("pathBD", this.perfiles.get(i).getPathBD());
			perfil.put("pathWS", this.perfiles.get(i).getPathWS());
			perfil.put("pathHTML", this.perfiles.get(i).getPathHTML());
			perfil.put("pathLayout", this.perfiles.get(i).getPathLayout());
			perfil.put("auditoria", this.perfiles.get(i).getAuditoria());
			perfil.put("geneSys", this.perfiles.get(i).getGeneSys());
			lista.add(perfil);
		}		
		obj.put("perfiles", lista);
		try (FileWriter file = new FileWriter(rutaJSON)) {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editPerfil(Perfil viejo, Perfil nuevo) {
		for(int i = 0; i < this.perfiles.size(); i++) {
			if(this.perfiles.get(i).igual(viejo)) {
				this.perfiles.get(i).setNombre(nuevo.getNombre());
				this.perfiles.get(i).setHost(nuevo.getHost());
				this.perfiles.get(i).setBd(nuevo.getBd());
				this.perfiles.get(i).setUser(nuevo.getUser());
				this.perfiles.get(i).setPwd(nuevo.getPwd());
				this.perfiles.get(i).setPathBD(nuevo.getPathBD());
				this.perfiles.get(i).setPathWS(nuevo.getPathWS());
				this.perfiles.get(i).setPathHTML(nuevo.getPathHTML());
				this.perfiles.get(i).setPathLayout(nuevo.getPathLayout());
				this.perfiles.get(i).setAuditoria(nuevo.getAuditoria());
				this.perfiles.get(i).setGeneSys(nuevo.getGeneSys());
			}
		}
		guardarPerfiles();
	}
	
	public void refreshPerfil() {
		try {
			this.perfiles.removeAllElements();
			Object obj = parser.parse(new FileReader(rutaJSON));
			JSONObject configJSON = (JSONObject) obj;
			JSONArray perfiles = (JSONArray) configJSON.get("perfiles");
			for(int i=0; i<perfiles.size(); i++) {
				JSONObject p = (JSONObject) perfiles.get(i);
				String nombre = (String) p.get("nombre");
				String host = (String) p.get("host");
				String bd = (String) p.get("bd");
				String user = (String) p.get("user");
				String pwd = (String) p.get("pwd");
				String pathBd = (String) p.get("pathBD");
				String pathWs = (String) p.get("pathWS");
				String pathHTML = (String) p.get("pathHTML");
				String pathLayout = (String) p.get("pathLayout");
				String auditoria = (String) p.get("auditoria");
				String geneSys = (String) p.get("geneSys");
				this.perfiles.add(new Perfil(nombre, host, bd, user, pwd, pathBd, pathWs, pathHTML, pathLayout, auditoria, geneSys));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
