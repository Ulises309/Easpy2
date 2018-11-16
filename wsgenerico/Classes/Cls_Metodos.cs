using Newtonsoft.Json;
using System.Data;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System;
using System.Web.Script.Serialization;

namespace WsPFApps.Classes
{
    public class Cls_Metodos
    {
        public enum ResponseTipo { Success, Error };

        public string ObtieneValorDesdeToken(string Tipo,ClaimsPrincipal principal)
        {
            string valor = principal.Claims.Where(c => c.Type == Tipo).Single().Value;
            return valor;
        }

        public string RandomString(int length)
        {
            System.Random r = new System.Random();
            const string pool = "abcdefghijklmnopqrstuvwxyz0123456789";
            var chars = Enumerable.Range(0, length).Select(x => pool[r.Next(0, pool.Length)]);
            return new string(chars.ToArray());
        }

        public static string GetJSONString_FTT(DataTable Dt)
        {
            string[] StrDc = new string[Dt.Columns.Count];
            string HeadStr = string.Empty;

            for (int i = 0; i < Dt.Columns.Count; i++)
            {
                StrDc[i] = Dt.Columns[i].Caption;
                HeadStr += "\"" + StrDc[i] + "\" : \"" + StrDc[i] + i.ToString() + "¾" + "\",";
            }

            HeadStr = HeadStr.Substring(0, HeadStr.Length - 1);

            StringBuilder Sb = new StringBuilder();
            Sb.Append("{\"" + Dt.TableName + "\" : [");

            for (int i = 0; i < Dt.Rows.Count; i++)
            {
                string TempStr = HeadStr;
                Sb.Append("{");

                for (int j = 0; j < Dt.Columns.Count; j++)
                {
                    TempStr = TempStr.Replace(Dt.Columns[j] + j.ToString() + "¾", Dt.Rows[i][j].ToString());
                }
                Sb.Append(TempStr + "},");
            }

            Sb = new StringBuilder(Sb.ToString().Substring(0, Sb.ToString().Length - 1));
            Sb.Append("]}");

            return Sb.ToString();
        }

        public static string GetJSONString(DataTable Dt)
        {
            string JSONString = string.Empty;
            JSONString = JsonConvert.SerializeObject(Dt);
            return JSONString;
        }

        public static string Response(string Mensaje, ResponseTipo Tipo, object OBJ = null)
        {
            try {
                //if (OBJ != null && Tipo == ResponseTipo.Error || OBJ.GetType() == typeof(System.NullReferenceException))
                if (OBJ != null && Tipo == ResponseTipo.Error)
                {
                    string msj = "{ Message:" + Mensaje + ", Description:" + ((Exception)OBJ).Message.ToString();
                    msj += "}";
                    
                    /*msj += ", StackTrace:" + ((Exception)OBJ).StackTrace.ToString() + "}";
                    msj += " - Source:" + ((System.NullReferenceException)OBJ).Source.ToString();
                    msj += " - Inner:" + ((System.NullReferenceException)OBJ).InnerException.ToString();
                    msj += " - Data:" + ((System.NullReferenceException)OBJ).Data.ToString();
                    msj += " - TargetSite:" + ((System.NullReferenceException)OBJ).TargetSite.ToString();
                    msj += " - HResult:" + ((System.NullReferenceException)OBJ).HResult.ToString();*/
                    return msj;
                }

                return Mensaje;
            }
            catch (Exception e) {
                return "SUPPA ERROR !";
            }
            
            
        }


    }
}