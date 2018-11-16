using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Claims;
using System.Web;
using System.Web.Http;
using System.Web.Http.Controllers;
using WsPFApps.Classes;
using WsPFApps.Controllers;

namespace WsPFApps
{
    public class AuthToken : AuthorizeAttribute
    {
        protected override bool IsAuthorized(HttpActionContext actionContext)
        {
            ClaimsPrincipal principal = actionContext.RequestContext.Principal as ClaimsPrincipal;
            Cls_Metodos metodos = new Cls_Metodos();
            try
            { 
                
                Cls_User clsUser = new Cls_User();
            
                string query = actionContext.Request.RequestUri.Query;
                var queryStrings = System.Web.HttpUtility.ParseQueryString(query);
            
                int usuario_id = Convert.ToInt32(metodos.ObtieneValorDesdeToken("idUsuario", principal));
                string token = metodos.ObtieneValorDesdeToken("token", principal);

                //RSA dec = new RSA("<RSAKeyValue><Modulus>t015WR/Q/hmDXmQp+Bf0vMLUsGWg6QOb/zt/RbVb4nvsbQA9bjHzMT1pw0UfFvJrxSnNUqsLRKUyy0UXpEgu1fB5nQUtSYzJk4dnEaOtwQHr7QLEW8+lhvefj35XXxurLUbzDZ79LodTmn2bNVPq4SZXKIDq+vm9iwWuaDWfqqaVRCw8O8dHg9nC4wXDaRTnZfon7GEzEUjpVPFH1xyYEUrqV7XbYkpxI05ruvqyYLEOkTZ7H5jGKjhEikl7CXrj6jYV3A3bsmunfz0eQRXef7xAR1gkkZ71KvnRYnukY4nUTTL8AWvrAl6+dugLduPz8HMX8mAcCSY2Rb5Ihkex9w==</Modulus><Exponent>AQAB</Exponent><P>395jT4DcwdSye1paCo38jvCIiBmuUKT0ziHlOE6/AzqBX5cN6YsIJ7yo0VuAJt/vwFDTJx6FmAXHGhV1MPEhwRfLPELtQNKRNXI/yIm7+2pqIutDFwOC8v/SuO1LTsUhQq0jRf+/O0sKcsrLOKt3i8yESm8IKIt4HxL5auKO98c=</P><Q>0ZyQ8fpevjy5n/8b6Qf4PFlB2c8b7+9unY9m1ioEcxffi67Kzd0mdnqW8WyfTDng+GoxxPhZKciMbMd9D2i/QZDwUotF2GOk25E8JN1SzcB0eNT1Jif+K9+PkN083TyFU0RB1NHQF7NSmlmdt6yqk9xKGKSHhFdNs82kpvBSVFE=</Q><DP>LRRuMRtE2O9IxZgrG4b9/ZaoF6f8NxcQiNXAf7cztaW5WZNkZMvvZ3kREZ94Tdu4PqTH8E+wHS3Eqxb5E1LrNiPhdBASEISWYwnPmEVMClOibcesKd02KFKlj0GRcZo2mWOd/8GBM6JnxzfY9l9o4kjbttVm8isrlC5Q9VoL6g8=</DP><DQ>RVc129anYOdVwMfzw7MPO2eLkTIiW80pGpZFvrNHpYtQODUqgc+W2brUFCmKyPycn5EBxdciJ+BgE9wqUt6j5ly8I5JPGLZd4wS4kLlmmwn/fbl1H5o7Sug55gJKECXlNRZvcKe33x9DRCxBMHG+PAuzt1M2QWFgIwr0XQVDNwE=</DQ><InverseQ>K6jXF/Q7vx3YhvO+6aDZ0ogICS6nkGEJpbf/dtEWlTxScFuSyj/ihai0cgg5yDWPyr54xRtu/FLytIrrt0GTx5YKfbMnQNxzcr3jNfxN7EKb8GNgbIjtly+4axvNIJGj1ANZJZJQi7MTx/ewZSvZiLaramtBwqGl6ZiUKVuyNZk=</InverseQ><D>QfUQuy8lpqlLigWEzI7iM6HvomGPWwk/csHX9B9kJsTst/QkJqi2l3s/uVH/8+PbP6DhDuQZM+Q1uMrtzOFkaPsU55lbSGHujrVkMwawmQi1+hzKxzaam0wzEBEoI0Lzf2FOSBJzi/CJ9sdHuBKwK7/+EMcCxrhiqcmrjjftchvPEzKfefVSGSy34lnrME8XjlTqbZQ9QQ/a+62STQ7Jg8bzT1xBe9fTN7m2tVyLElfayy4eB8fXlbFjRyJiLZmzYjpSqnuTC9rEgVds2qc918HBWBX0z9vgpmaqFdU39hEs+R/F7dD0qkxpoAO4oIKiff0YUCy0Mhzw5wyGYltwQQ==</D></RSAKeyValue>", "");
                //string d = dec.Decrypt("mrL+i/7qlZVgX23izK6xgZ7vZkt0WLxAO+g6XpWWIrkANzDNA7vLVHhUz65R/+9Q5M7UZNmDPHijIGwjsz7BaU9j+fiNJihlZGp5v9ah0Q/quK2ZTpiKhNovUzxZJ9GDOvHmT1j8HGzuu3EYJDc2XjFtMQ121suxzooELD0RFB4TsOvXA8c54L5d+jDhvOhyAAqg5WmioMC32rAXWHuM6ZjMgPRxjrSlBjh/69kjcwNbEY3+QtkAza6xA8oSzY8YLgAtp0M2BedzbEZnnw1Fd9qPBPY4a/7R9ytBAQ861jXwKXHZtB2tL4nxmORgNbu9iVMrbzOY8sJi2058QoyvcA==");

                int numeroPeticion;
                string auth_token_test = ConfigurationManager.AppSettings["auth_token_test"].ToString();
                string auth_token = actionContext.Request.Headers.GetValues("auth_token").FirstOrDefault();//"byaGultH3EmlfGhc8bA/Abtb6aFv+QeV9qvRnEGyunUsyRPqJe2EbUrgsYyzFBg16mYTu5B+WjqYoZEDHAktGKOeKpzRwlq7X3CXEE8DLQoXLiaY8avpF66BFcWzWIcqU11JMaROfDp2QM9k6smaymyn5Z25NV0bQMeQ3ltxGvoDVjXurZil99aaO8O81RPaUwWZUb0y9WM82hmRhDaVWuQLXEh5//8kJYvAm84cX3QK/oP1Okx7bG2wpXHuqYD8HmBVmJD1sCpd13rLwHpv2sOwpDLtY45OiqDFRbOOw9Y3lRZqCcoD636NwR6J2aPKFCk3DZNAuET+IjO6RGl48A==";//queryStrings["auth_token"];

                if (auth_token_test.Length > 0 && auth_token == auth_token_test)
                {
                    numeroPeticion = 0;
                }
                else
                {
                    RSA dec = new RSA(clsUser.ObtenerLlavePrimaria(usuario_id), "");
                    string decrypt = dec.Decrypt(auth_token);

                    bool result = Int32.TryParse(decrypt, out numeroPeticion);
                    if (!result)
                    {
                        throw new ArgumentException("Problema con RSA");
                    }
                }

                Cls_User user = new Cls_User();
                DataRow row = user.VerificarToken(usuario_id, token, numeroPeticion);
                if (Convert.ToInt32(row["valido"]) == 0)
                {
                    throw new ArgumentException(row["mensaje"].ToString());
                }
            
                return base.IsAuthorized(actionContext);
            }
            catch (Exception exc)
            {
                actionContext.Response = new HttpResponseMessage
                {
                    StatusCode = HttpStatusCode.Unauthorized,
                    Content = new StringContent("{\"exito\":0, \"mensaje\":\"" + exc.Message + "\", \"logout\":1}", System.Text.Encoding.UTF8, "application/json")
                };
                int usuario_id = Convert.ToInt32(metodos.ObtieneValorDesdeToken("idUsuario", principal));
                Cls_User.Logout(usuario_id);
                return false;
            }
        }

        protected override void HandleUnauthorizedRequest(HttpActionContext actionContext)
        {
            //actionContext.Response = new HttpResponseMessage
            //{
            //    StatusCode = HttpStatusCode.Forbidden,
            //    Content = new StringContent("You are unauthorized to access this resource")
            //};
            if (actionContext.Response.StatusCode != HttpStatusCode.Unauthorized)
            {
                actionContext.Response = new HttpResponseMessage
                {
                    StatusCode = HttpStatusCode.BadRequest,
                    Content = new StringContent("{\"mensaje\":\"Sesion caducada\"}",
                System.Text.Encoding.UTF8,
                "application/json")
                };
            }
            
        }
    }
}