using Microsoft.Owin;
using Owin;
using System;
using System.Web.Http;
using Microsoft.Owin.Security.OAuth;
using System.Web.Configuration;
using System.Web.Http.Cors;
using Microsoft.Owin.Cors;
using System.Threading;
using System.Web.Cors;
using System.Threading.Tasks;
using System.Configuration;

[assembly: OwinStartup("ProductionConfiguration",typeof(WsPFApps.Startup))]
namespace WsPFApps
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            var appSettings = WebConfigurationManager.AppSettings;
            HttpConfiguration config = new HttpConfiguration();
            ConfigureOAuth(app);
            WebApiConfig.Register(config);

            // If CORS settings are present in Web.config
            if (!string.IsNullOrWhiteSpace(appSettings["cors:Origins"]))
            {
                // Load CORS settings from Web.config
                var corsPolicy = new EnableCorsAttribute(
                    appSettings["cors:Origins"],
                    appSettings["cors:Headers"],
                    appSettings["cors:Methods"]);

                // Enable CORS for ASP.NET Identity
                app.UseCors(new CorsOptions
                {
                    PolicyProvider = new CorsPolicyProvider
                    {
                        PolicyResolver = request =>
                            request.Path.Value == "/token" ?
                            corsPolicy.GetCorsPolicyAsync(null, CancellationToken.None) :
                            Task.FromResult<CorsPolicy>(null)
                    }
                });
               
                // Enable CORS for Web API
                //config.EnableCors(corsPolicy);
            }
            else
            {
              

                
                app.UseCors(Microsoft.Owin.Cors.CorsOptions.AllowAll);
               
            }

            app.UseWebApi(config);
        }
        
        public void ConfigureOAuth(IAppBuilder app)
        {

            OAuthAuthorizationServerOptions OAuthServerOptions = new OAuthAuthorizationServerOptions()
            {
                AllowInsecureHttp = true,
                TokenEndpointPath = new PathString("/login"),
                Provider = new Providers.SimpleAuthorizationServerProvider(),
                AccessTokenExpireTimeSpan = TimeSpan.FromMinutes(int.Parse(ConfigurationManager.AppSettings["tiempo_sesion"]))
            };

            // Token Generation
            app.UseOAuthAuthorizationServer(OAuthServerOptions);
            app.UseOAuthBearerAuthentication(new OAuthBearerAuthenticationOptions());
        }
    }
}