using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace WsPFApps.Classes
{
    public class Cls_App
    {
        private Cls_BD.Cls_Coneccion conBD = new Cls_BD.Cls_Coneccion();

        public DataRow InformacionApp(int app_id, int plataforma_id)
        {
            conBD.SetCommand("WS.SP_AppVersionCON");

            conBD.CreateParameter("@idApp", app_id);
            conBD.CreateParameter("@idPlataforma", plataforma_id);

            return conBD.getDataSet().Tables[0].Rows[0];
        }
    }
}