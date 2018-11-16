using System;
using System.Configuration;
using System.Data;

namespace WsPFApps.Classes
{
    public class Cls_User
    {
        private Cls_BD.Cls_Coneccion conBD = new Cls_BD.Cls_Coneccion();
        public static int IDUSER = -1;

        public DataTable Login(string usuario, string clave, string token, string privateKey)
        {
            String sp = conBD.esquema + ".[SP_ValidarUsuario]";
            conBD.SetCommand(sp);
            conBD.CreateParameter("@usuario", usuario, usuario.Length);
            conBD.CreateParameter("@clave", clave, clave.Length);
            conBD.CreateParameter("@token", token, token.Length);
            //conBD.CreateParameter("@idApp", Convert.ToInt16(ConfigurationManager.AppSettings["idApp"].ToString()));
            conBD.CreateParameter("@privateKey", privateKey, privateKey.Length);

            return conBD.getDataSet().Tables[0];
        }

        public DataRow VerificarToken(int usuario_id, string token, int numeroPeticion)
        {
            IDUSER = usuario_id;

            conBD.SetCommand(conBD.esquema + ".[SP_UsuarioValidarToken]");
            conBD.CreateParameter("@idUsuario", usuario_id);
            conBD.CreateParameter("@token", token);
            conBD.CreateParameter("@numeroPeticion", numeroPeticion);
            return conBD.getDataSet().Tables[0].Rows[0];
        }

        public string ObtenerLlavePrimaria(int usuario_id)
        {
            conBD.SetCommand(conBD.esquema + ".[SP_ObtenerLlavePrimariaUsuario]");
            conBD.CreateParameter("@idUsuario", usuario_id);
            return conBD.getDataSet().Tables[0].Rows[0]["privateKey"].ToString();
        }

        public static void Logout(int id_usuario)
        {
            Cls_BD.Cls_Coneccion CONN = new Cls_BD.Cls_Coneccion();
            CONN.SetCommand(CONN.esquema + ".[LogOut]");
            CONN.CreateParameter("@Id_Usuario", id_usuario);

            CONN.getDataSet();
        }
    }
}