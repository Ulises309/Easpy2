namespace WsPFApps.Models
{
    public class Settings
    {
        public static string _BacklogDB = System.Configuration.ConfigurationManager.ConnectionStrings["BacklogDB"].ConnectionString;
        public static string _esquema = System.Configuration.ConfigurationManager.AppSettings["esquema"].ToString();
        public static string _obtenerSp = _esquema + "." + System.Configuration.ConfigurationManager.AppSettings["obtenerSp"].ToString();
        public static string _obtenerDataSet = _esquema + "." + System.Configuration.ConfigurationManager.AppSettings["obtenerDataSet"].ToString();
        public static string _comboPadre = _esquema + "." + System.Configuration.ConfigurationManager.AppSettings["comboPadre"].ToString();
        public static string _listaReportes = _esquema + "." + System.Configuration.ConfigurationManager.AppSettings["listaReportes"].ToString();
        public static string _camposReportes = _esquema + "." + System.Configuration.ConfigurationManager.AppSettings["camposReportes"].ToString();
        public static string _menuReportes = _esquema + "." + System.Configuration.ConfigurationManager.AppSettings["menuReportes"].ToString();
        public static string _cambiarPassword = _esquema + "." + System.Configuration.ConfigurationManager.AppSettings["cambiarPassword"].ToString();
        //public static string strKey = System.Environment.GetEnvironmentVariable(System.Configuration.ConfigurationManager.AppSettings["VariableConexion"].ToString());
    }
}