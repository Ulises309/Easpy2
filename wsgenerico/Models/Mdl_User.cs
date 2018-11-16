using System.ComponentModel.DataAnnotations;

namespace WsPFApps.Models
{
    public class Usuario
    {
        public int id { get; set; }
        public int tipoUsuario_Id { get; set; }
        public string token { get; set; }
        public int cambiarPassword { get; set; }

        public Usuario()
        {
            this.id = 0;
            this.token = "";
        }
    }
}