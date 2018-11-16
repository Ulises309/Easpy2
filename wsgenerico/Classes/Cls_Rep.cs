using System.Data;
using System.Data.SqlClient;
using System.Configuration;
using WsPFApps.Models;
using System;
using System.Xml;
using System.Data.SqlTypes;

namespace WsPFApps.Classes
{


    /// <summary>
    /// Summary description for cls_Rep
    /// </summary>
    public class cls_Rep
    {
        private Cls_BD.Cls_Coneccion conBD = new Cls_BD.Cls_Coneccion();
        public int result_number;
        public string result_msg;
        public string Esquema = ConfigurationManager.AppSettings["Esquema"].ToString();
        public string SPString;

        public cls_Rep()
        {
        }
        
        public DataSet Rep_Catalagos(int Usuarioid, int Companiaid, string Tipo)
        {

            SPString = Esquema.ToString() + ".Rep_Catalagos";

            conBD.SetCommand(SPString);

            conBD.CreateParameter("@Usuarioid", Usuarioid);
            conBD.CreateParameter("@Companiaid", Companiaid);
            conBD.CreateParameter("@Tipo", Tipo, Tipo.Length);


            return conBD.getDataSet();

        }

        public DataSet Rep_ArchivoXML(int Usuario, int ReporteId, string SP)
        {
            SPString = Esquema.ToString() + ".Rep_EnXmlRuta";

            conBD.SetCommand(SPString);

            conBD.CreateParameter("@Usuario", Usuario);

            conBD.CreateParameter("@Reporte", ReporteId);
            conBD.CreateParameter("@SP", SP, SP.Length);


            return conBD.getDataSet();

        }

        public DataSet Rep_Reportes(int Usuarioid, int SistemaId)//, int Companiaid)
        {

            SPString = Esquema.ToString() + ".ConsultaReportes";

            conBD.SetCommand(SPString);

            conBD.CreateParameter("@Usuario", Usuarioid);
            if (SistemaId != 0)
            {
                conBD.CreateParameter("@Sistema", SistemaId);
            }

            return conBD.getDataSet();

        }
        public DataSet Rep_Reporte(int Usuarioid, int SistemaId, int ReporteId)//, int Companiaid)
        {

            DataSet ds = new DataSet();


            SPString = Esquema.ToString() + ".ConsultaReporte";

            conBD.SetCommand(SPString);


            conBD.CreateParameter("@Usuario", Usuarioid);
            if (SistemaId != 0)
            {
                conBD.CreateParameter("@Sistema", SistemaId);
            }
            if (ReporteId != 0)
            {
                conBD.CreateParameter("@ReporteId", ReporteId);
            }

            ds = conBD.getDataSet();
            result_number = conBD.result_number;
            result_msg = conBD.result_msg;

            return ds;

        }

        public DataSet Rep_Ejecuta(string Query, int @ReporteId, int UsuarioId, int SistemaId)
        {
            DataSet ds = new DataSet();



            SPString = Esquema.ToString() + ".Ejecuta";
            conBD.SetCommand(SPString);
            conBD.CreateParameter("@Query", Query, Query.Length);
            conBD.CreateParameter("@ReporteId", ReporteId);
            conBD.CreateParameter("@usuarioId", UsuarioId);
            if (SistemaId != 0)
            {
                conBD.CreateParameter("@SistemaId", SistemaId);
            }
            ds = conBD.getDataSet();
            result_number = conBD.result_number;
            result_msg = conBD.result_msg;
            return ds;
        }

        public SqlDataReader Rep_Ejecuta(string Query)
        {
            SPString = Esquema.ToString() + ".Ejecuta";
            Query = SPString.ToString() + " " + Query;
            SqlDataReader ds = conBD.getDataReader(Query, 999999);
            result_number = conBD.result_number;
            result_msg = conBD.result_msg;
            return ds;
        }
        
        public DataSet Rep_Campos(int Reporte)
        {
            SPString = Esquema.ToString() + ".VerCampos";
            conBD.SetCommand(SPString);
            conBD.CreateParameter("@ReporteId", Reporte);
            return conBD.getDataSet();
        }

        public DataSet Rep_Campos(int Reporte, int Tipo)
        {
            SPString = Esquema.ToString() + ".VerCampos";
            conBD.SetCommand(SPString);
            conBD.CreateParameter("@ReporteId", Reporte);
            conBD.CreateParameter("@Tipo", Tipo);
            return conBD.getDataSet();
        }



        public DataSet Rep_Actualiza(int Reporte)
        {
            SPString = Esquema.ToString() + ".Actualiza";
            conBD.SetCommand(SPString);
            conBD.CreateParameter("@ReporteId", Reporte);
            return conBD.getDataSet();

        }
        public DataSet Con_MenuOpcion(int Usuario, int Padre, int SistemaId)
        {
            SPString = Esquema.ToString() + ".[Sel_MenuOpcion2]";
            conBD.SetCommand(SPString);
            conBD.CreateParameter("@id_Usuario", Usuario);
            conBD.CreateParameter("@Padre", Padre);
            if (SistemaId != 0)
            {
                conBD.CreateParameter("@SistemaId", SistemaId);
            }
            return conBD.getDataSet();

        }
        
        public void Rep_Close()
        {
            if (conBD != null) {
                conBD.Close();
            }
        }
    }
}