using System;
using System.Collections.Generic;
using System.Data;
using Microsoft.Owin.Security;
using Microsoft.Owin.Security.OAuth;
using System.Threading.Tasks;
using System.Security.Claims;
using WsPFApps.Models;
using WsPFApps.Classes;
using Newtonsoft.Json;
using System.Web.Helpers;

namespace WsPFApps.Providers
{
    public class SimpleAuthorizationServerProvider : OAuthAuthorizationServerProvider
    {

        Cls_User clsUser = new Cls_User();

        public override async Task ValidateClientAuthentication(OAuthValidateClientAuthenticationContext context)
        {
            context.Validated();
        }

        public override Task TokenEndpoint(OAuthTokenEndpointContext context)
        {
            foreach (KeyValuePair<string, string> property in context.Properties.Dictionary)
            {
                context.AdditionalResponseParameters.Add(property.Key, property.Value);
            }

            return Task.FromResult<object>(null);
        }

        public override async Task GrantResourceOwnerCredentials(OAuthGrantResourceOwnerCredentialsContext context)
        {
            RSA rsa = new RSA();

            context.OwinContext.Response.Headers.Add("Access-Control-Allow-Origin", new[] { "*" });
            
            Cls_Metodos clsMetodos = new Cls_Metodos();
            string token = clsMetodos.RandomString(4);
            Usuario usuario = ValidaUsuario(context.UserName, context.Password, token, rsa.privateKey);
            
            if (usuario.id <= 0)
            {
                context.SetError("invalid_grant", "El usuario no esta autorizado para usar la aplicacion");

                return;
            }
            //Estas variables son obligatorias
            var identity = new ClaimsIdentity(context.Options.AuthenticationType);
            identity.AddClaim(new Claim("idUsuario", usuario.id.ToString()));
            identity.AddClaim(new Claim("token", usuario.token.ToString()));
            identity.AddClaim(new Claim("TipoUsuario_Id", usuario.tipoUsuario_Id.ToString()));
            identity.AddClaim(new Claim("CambiarPassword", usuario.cambiarPassword.ToString()));
            /*
             * Aqui agrega lo que quieras ingresar al token, leyendolo del objeto usuario
             */
            

            AuthenticationProperties props = new AuthenticationProperties(new Dictionary<string, string>
            {
                {"exito", "1"},
                {"idUsuario", usuario.id.ToString()},
                {"mensaje", "Sesion iniciada correctamente"},
                {"auth_key", rsa.publicKey},
                {"cambiarPassword", usuario.cambiarPassword.ToString() }
            });

            AuthenticationTicket ticket = new AuthenticationTicket(identity, props);
            context.Validated(ticket);
        }

        private Usuario ValidaUsuario(string usuario, string clave, string token, string privateKey = "")
        {
            DataTable dt = clsUser.Login(usuario, clave, token, privateKey);
            Usuario u = new Usuario();

            if (dt.Rows.Count > 0 && Convert.ToInt16(dt.Rows[0]["valido"]) == 1)
            {
                u = new Usuario
                {
                    id = Convert.ToInt32(dt.Rows[0]["Id_Usuario"].ToString()),
                    tipoUsuario_Id = Convert.ToInt32(dt.Rows[0]["TipoUsuario_Id"].ToString()),
                    token = dt.Rows[0]["token"].ToString(),
                    cambiarPassword = Convert.ToInt32(dt.Rows[0]["CambiarPassword"].ToString())
                    /*
                     * Aqui añadimos las variables que deseamos guardar, leyendolas desde
                     * la base de datos e ingresandolas a un objeto tipo Usuario.
                    */
                };
            }
            else
            {
                u = new Usuario
                {
                    id = 0
                };
            }

            return u;
        }
    }
}