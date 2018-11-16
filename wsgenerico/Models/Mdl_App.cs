using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WsPFApps.Models
{
    public class App
    {
        public int id { get; set; }
        public string nombre { get; set; }
        public int plataforma_id { get; set; }
        public decimal version { get; set; }
        public string fecha_lanzamiento { get; set; }
    }
}