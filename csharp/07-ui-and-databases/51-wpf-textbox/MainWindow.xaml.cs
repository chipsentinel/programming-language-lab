using System.Windows;

namespace WpfTextBox
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        // Botón Ingresar: lee los cuadros de texto, valida y muestra el resultado
        private void BtnIngresar_Click(object sender, RoutedEventArgs e)
        {
            // .Text es lo que hay escrito; Trim() quita los espacios sobrantes de los extremos
            string nombre = txtNombre.Text.Trim();
            string edad = txtEdad.Text.Trim();

            // IsNullOrEmpty: true si el texto es null o está vacío ("")
            if (string.IsNullOrEmpty(nombre) && string.IsNullOrEmpty(edad))
            {
                lResultado.Content = "No colocaste ningún dato";
            }
            else if (string.IsNullOrEmpty(nombre))
            {
                lResultado.Content = "No colocaste nombre";
            }
            else if (string.IsNullOrEmpty(edad))
            {
                lResultado.Content = "No colocaste edad";
            }
            else
            {
                // Todos los datos están: se muestran
                lResultado.Content = "Nombre: " + nombre + ", Edad: " + edad;
            }
        }

        // Botón Borrar: limpia los cuadros de texto y restablece la etiqueta
        private void BtnBorrar_Click(object sender, RoutedEventArgs e)
        {
            txtNombre.Text = "";              // en un TextBox es Text...
            txtEdad.Text = "";
            lResultado.Content = "Resultado"; // ...y en un Label es Content. No se vacía: orienta al usuario
        }
    }
}
