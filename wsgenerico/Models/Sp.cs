using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WsPFApps.Models
{
    public class Sp
    {
        public int sp { get; set; }
        public JArray parametros { get; set; }
    }
}