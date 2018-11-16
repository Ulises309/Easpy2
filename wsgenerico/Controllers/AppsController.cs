using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Claims;
using System.Web.Http;
using WsPFApps.Classes;
using WsPFApps.Models;

namespace WsPFApps.Controllers
{
    [AuthToken]
    [RoutePrefix("api/apps")]
    public class AppsController : ApiController
    {
        Cls_App obj_app = new Cls_App();
        Cls_Metodos metodos = new Cls_Metodos();
        [AcceptVerbs("GET")]
        [HttpGet]

        [Route("{app_id}")]
        public object InformacionApp(int app_id, int plataforma_id)
        {
            DataRow row = obj_app.InformacionApp(app_id, plataforma_id);

            App app = new App{
                id = Convert.ToInt32(row["idApp"].ToString()),
                nombre = row["nombre"].ToString(),
                plataforma_id = Convert.ToInt32(row["idPlataforma"].ToString()),
                version = Convert.ToDecimal(row["version"].ToString()),
                fecha_lanzamiento = row["fechaLanzamiento"].ToString()
            };

            return new { app = app };
        }
    }
}
