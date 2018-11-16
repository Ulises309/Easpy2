using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using WsPFApps.Classes;
using System.Data;
using Newtonsoft.Json.Linq;
using WsPFApps.Models;
using System.Security.Claims;
using System.Net.Http;
using System.Web.Script.Serialization;
using System.Net;

namespace WsPFApps.Controllers
{
    public class GenericoController : ApiController
    {
        Cls_App obj_app = new Cls_App();
        Cls_Metodos MTDS = new Cls_Metodos();
        Cls_BD.Cls_Coneccion CONN = new Cls_BD.Cls_Coneccion();
        JavaScriptSerializer JSONSerializer = new JavaScriptSerializer();

        [AuthToken]
        [HttpPost]
        public HttpResponseMessage Exec(Sp c)
        {
            try
            {
                //Extrae variables del token para la comprobacion de permisos y registro de log
                ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
                int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
                int idTipoUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("TipoUsuario_Id", principal));
                int cambiarPassword = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("CambiarPassword", principal));

                if (cambiarPassword == 1)
                    return Request.CreateResponse(HttpStatusCode.PreconditionFailed, "Debes cambiar tu clave", Configuration.Formatters.JsonFormatter);


                //Crea la data table que se alimentara de los datos del resultado del stored procedure
                DataTable d;

                //Consulta el nombre y campos del store a ejecutar
                CONN.SetCommand(Settings._obtenerSp);
                CONN.CreateParameter("@sp", c.sp);
                CONN.CreateParameter("@usuario_id", idUsuario);
                CONN.CreateParameter("@tipousuario_id", idTipoUsuario);
                d = CONN.getDataTable();

                if(d.Rows.Count == 0)
                    return Request.CreateResponse(HttpStatusCode.Conflict, "No tienes permisos para ejecutar esta accion", Configuration.Formatters.JsonFormatter);


                //Extrae el nombre del sp de la consulta
                String store = d.Rows[0][0].ToString();


                //Agrega el nombre del store a ejecutar
                CONN.SetCommand(store);

                //Convierte a un arreglo JSON los parametros recibidos del JSON String
                JArray array = c.parametros;

                //Recorre los objectos del arreglo JSON
                foreach (JObject content in array.Children<JObject>())
                {
                    foreach (JProperty prop in content.Properties())
                    {
                        //Bandera de comprobacion para ver si el parametro a comprobar se encuentra en la lista de parametros del store procedure
                        bool existe = false;
                        //Recorre cada parametro del store
                        foreach (DataRow row in d.Rows)
                        {
                            //Le quita el @ al nombre del campo del store
                            String param = row["Titulo_Pagina"].ToString().TrimStart('@');
                            //Comprueba que el parametro enviado del JSON exista en la lista de parametros del store (Sin sensivilidad a mayusculas)
                            if (prop.Name.ToUpper() == param.ToUpper())
                            {
                                //Enciende la bandera de que el campo existe
                                existe = true;
                                //Castea el valor del parametro depende del tipo de este y lo agrega al query
                                switch (row["Variable_Id"])
                                {
                                    case 1:
                                        if ((int)row["Control_Id"] == 3)
                                            CONN.CreateParameter("@" + param, (DateTime)prop.Value);
                                        else
                                            CONN.CreateParameter("@" + param, (String)prop.Value);
                                        break;
                                    case 2:
                                        if (((string)prop.Value).Equals("true"))
                                            prop.Value = 1;
                                        if (((string)prop.Value).Equals("false"))
                                            prop.Value = 0;
                                        CONN.CreateParameter("@" + param, (int)prop.Value);
                                        break;
                                    case 3:
                                        String sessionstring = MTDS.ObtieneValorDesdeToken((String)row["Valor_Variable"], principal);
                                        CONN.CreateParameter("@" + param, sessionstring);
                                        break;
                                    case 4:
                                        int sessionint = Convert.ToInt32(MTDS.ObtieneValorDesdeToken((String)row["Valor_Variable"], principal));
                                        CONN.CreateParameter("@" + param, sessionint);
                                        break;
                                    case 5:
                                        CONN.CreateParameter("@" + param, DBNull.Value);
                                        break;
                                    case 6:
                                        CONN.CreateParameter("@" + param, (String)prop.Value);
                                        break;
                                    case 7:
                                        CONN.CreateParameter("@" + param, (String)prop.Value);
                                        break;
                                    default:
                                        CONN.CreateParameter("@" + param, (String)prop.Value);
                                        break;
                                }
                            }
                        }
                        //Devuelve un error en caso de que no se encienda la bandera de existencia
                        if (!existe)
                        {
                            //Nota* Guardar datos enviados para auditoria o detectar hackers
                            //return Cls_Metodos.Response("El parametro " + prop.Name + " no existe en el metodo", Cls_Metodos.ResponseTipo.Error);
                            return Request.CreateResponse(HttpStatusCode.BadRequest, "El parametro " + prop.Name + " no existe en el metodo", Configuration.Formatters.JsonFormatter);
                        }
                    }
                }
                //Obtiene el resultado del query
                d = CONN.getDataTable();
                //Devuelve el resultado del query
                return Request.CreateResponse(HttpStatusCode.OK, d, Configuration.Formatters.JsonFormatter);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.Conflict, "Error al ejecutar, intente de nuevo", Configuration.Formatters.JsonFormatter);
            }
        }

        [HttpPost]
        public HttpResponseMessage checarLogin()
        {
            try
            {
                ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
                int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
                int idTipoUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("TipoUsuario_Id", principal));
                int cambiarPassword = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("CambiarPassword", principal));

                if (cambiarPassword == 1)
                    return Request.CreateResponse(HttpStatusCode.PreconditionFailed, "Debes cambiar tu clave", Configuration.Formatters.JsonFormatter);


                if (idUsuario != null || idUsuario > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, "Login Valido", Configuration.Formatters.JsonFormatter);
                }
                    return Request.CreateResponse(HttpStatusCode.Unauthorized, "Error al ejecutar, intente de nuevo", Configuration.Formatters.JsonFormatter);

            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.Unauthorized, "Error al ejecutar, intente de nuevo", Configuration.Formatters.JsonFormatter);
            }
        }

        [AuthToken]
        [HttpPost]
        public HttpResponseMessage cambiarPassword(Sp c)
        {
            ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
            int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
            DataTable d;

            string password = c.parametros.First()["password"].ToString();
            string passwordVerifica = c.parametros.Last()["passwordConfirma"].ToString();

            if(!password.Equals(passwordVerifica))
                return Request.CreateResponse(HttpStatusCode.BadRequest, new { mensaje= "Las contraseñas no coinciden" }, Configuration.Formatters.JsonFormatter);

            CONN.SetCommand(Settings._cambiarPassword);
            CONN.CreateParameter("@Id_Usuario", idUsuario);
            CONN.CreateParameter("@Password", password);

            d = CONN.getDataTable();
            Cls_User.Logout(idUsuario);
            return Request.CreateResponse(HttpStatusCode.OK, d, Configuration.Formatters.JsonFormatter);
        }

        [AuthToken]
        [HttpPost]
        public HttpResponseMessage obtenerDataSet(Campo c)
        {
            ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
            int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
            int idTipoUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("TipoUsuario_Id", principal));
            DataTable d;
            CONN.SetCommand(Settings._obtenerDataSet);
            CONN.CreateParameter("@ID_Campo", c.ID_Campo);
            CONN.CreateParameter("@usuario_id", idUsuario);
            CONN.CreateParameter("@tipousuario_id", idTipoUsuario);
            d = CONN.getDataTable();
            return Request.CreateResponse(HttpStatusCode.OK, d, Configuration.Formatters.JsonFormatter);
        }

        [AuthToken]
        [HttpPost]
        public HttpResponseMessage comboPadre(Campo c)
        {
            ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
            int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
            int idTipoUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("TipoUsuario_Id", principal));
            DataTable d;

            //Consulta el nombre y campos del store a ejecutar
            CONN.SetCommand(Settings._comboPadre);
            CONN.CreateParameter("@ID_Campo", c.ID_Campo);
            CONN.CreateParameter("@usuario_id", idUsuario);
            CONN.CreateParameter("@tipousuario_id", idTipoUsuario);
            d = CONN.getDataTable();

            //Extrae el nombre del sp de la consulta
            String store = d.Rows[0]["DataSet"].ToString();

            //Agrega el nombre del store a ejecutar
            CONN.SetCommand(store);

            //Convierte a un arreglo JSON los parametros recibidos del JSON String
            List<String> parametros = new List<String>();
            JArray array = c.valores;

            //Recorre los objectos del arreglo JSON
            foreach (JObject content in array.Children<JObject>())
            {
                foreach (JProperty prop in content.Properties())
                {
                    CONN.CreateParameter("@" + prop.Name, (int)prop.Value);
                }
            }

            d = CONN.getDataTable();
            return Request.CreateResponse(HttpStatusCode.OK, d, Configuration.Formatters.JsonFormatter);

        }

        [AuthToken]
        [HttpPost]
        public HttpResponseMessage listaReportes()
        {
            ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
            int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
            int idTipoUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("TipoUsuario_Id", principal));
            DataTable d;
            CONN.SetCommand(Settings._listaReportes);
            CONN.CreateParameter("@usuario_id", idUsuario);
            CONN.CreateParameter("@tipousuario_id", idTipoUsuario);
            d = CONN.getDataTable();
            return Request.CreateResponse(HttpStatusCode.OK, d, Configuration.Formatters.JsonFormatter);
        }

        [AuthToken]
        [HttpPost]
        public HttpResponseMessage camposReporte(Campo c)
        {
            ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
            int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
            int idTipoUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("TipoUsuario_Id", principal));
            DataTable d;
            CONN.SetCommand(Settings._camposReportes);
            CONN.CreateParameter("@Reporte_Id", c.Reporte_Id);
            CONN.CreateParameter("@usuario_id", idUsuario);
            CONN.CreateParameter("@tipousuario_id", idTipoUsuario);
            d = CONN.getDataTable();
            return Request.CreateResponse(HttpStatusCode.OK, d, Configuration.Formatters.JsonFormatter);
        }

        [AuthToken]
        [HttpPost]
        public HttpResponseMessage menuReporte()
        {
            ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
            int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
            int idTipoUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("TipoUsuario_Id", principal));
            DataTable d;
            CONN.SetCommand(Settings._menuReportes);
            CONN.CreateParameter("@usuario_id", idUsuario);
            CONN.CreateParameter("@tipousuario_id", idTipoUsuario);
            d = CONN.getDataTable();
            return Request.CreateResponse(HttpStatusCode.OK, d, Configuration.Formatters.JsonFormatter);
        }

        [AuthToken]
        [HttpPost]
        public void logout()
        {
            ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
            int idUsuario = Convert.ToInt32(MTDS.ObtieneValorDesdeToken("idUsuario", principal));
            Cls_User.Logout(idUsuario);
        }

        public class Campo
        {
            public int Reporte_Id { get; set; }
            public int ID_Campo { get; set; }
            public int valor { get; set; }
            public JArray valores { get; set; }
        }
    }
}