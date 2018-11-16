package noxcuse.logica;

public class Perfil {
	private String nombre;
	private String host;
	private String bd;
	private String user;
	private String pwd;
	private String pathBD;
	private String pathWS;
	private String pathHTML;
	private String pathLayout;
	private String auditoria;
	private String geneSys;
	
	public Perfil(String nombre, String host, String bd, String user, String pwd, String pathBD, String pathWS, String pathHTML, String pathLayout, String auditoria, String geneSys) {
		super();
		this.nombre = nombre;
		this.host = host;
		this.bd = bd;
		this.user = user;
		this.pwd = pwd;
		this.pathBD = pathBD;
		this.pathWS = pathWS;
		this.pathHTML = pathHTML;
		this.pathLayout = pathLayout;
		this.auditoria = auditoria;
		this.geneSys = geneSys;
	}
	
	public boolean igual(Object obj) {
		if (obj == null) {
	        return false;
	    }
	    if (!Perfil.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final Perfil p = (Perfil) obj;
	    if(
	    		!this.nombre.equals(p.getNombre()) ||
	    		!this.host.equals(p.getHost()) ||
	    		!this.bd.equals(p.getBd()) ||
	    		!this.user.equals(p.getUser()) ||
	    		!this.pwd.equals(p.getPwd()) ||
	    		!this.pathBD.equals(p.getPathBD()) ||
	    		!this.pathWS.equals(p.getPathWS()) ||
	    		!this.pathHTML.equals(p.getPathHTML()) ||
	    		!this.pathLayout.equals(p.getPathLayout()) ||
	    		!this.auditoria.equals(p.getAuditoria()) ||
	    		!this.geneSys.equals(p.getGeneSys())
	    ) {
	    	return false;
	    }
	    return true;
	}
	
	public String toString() {
		return nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getGeneSys() {
		return geneSys;
	}

	public void setGeneSys(String nombre) {
		this.geneSys = nombre;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	public String getPathBD() {
		return pathBD;
	}

	public void setPathBD(String pathBD) {
		this.pathBD = pathBD;
	}

	public String getPathWS() {
		return pathWS;
	}

	public void setPathWS(String pathWS) {
		this.pathWS = pathWS;
	}

	public String getPathHTML() {
		return pathHTML;
	}

	public void setPathHTML(String pathHTML) {
		this.pathHTML = pathHTML;
	}

	public String getPathLayout() {
		return pathLayout;
	}

	public void setPathLayout(String pathLayout) {
		this.pathLayout = pathLayout;
	}

	public String getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(String auditoria) {
		this.auditoria = auditoria;
	}
	
	
}
