using Microsoft.AspNet.Identity.EntityFramework;

namespace WsPFApps
{
    public class AuthContext : IdentityDbContext<IdentityUser>
    {
        public AuthContext()
            : base("AuthContext")
        {

        }
    }
}