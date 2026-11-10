using System.Windows;

namespace WpfButtonsLabels
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

            // El estado inicial también se puede fijar desde el código (en el XAML ya está IsEnabled="False"):
            // botonDespedir.IsEnabled = false;
        }

        // Evento del botón Saludar: se ejecuta al pulsarlo
        private void BotonSaludar_Click(object sender, RoutedEventArgs e)
        {
            // Cambiar el contenido de la etiqueta: nombreDelElemento.Content
            labelMensaje.Content = "Saludos a todos";

            // IsEnabled: si el botón se puede pulsar (true) o está desactivado (false)
            botonDespedir.IsEnabled = true;     // ya se saludó: ahora se puede despedir
            botonSaludar.IsEnabled = false;     // no hace falta saludar otra vez
        }

        // Evento del botón Despedir
        private void BotonDespedir_Click(object sender, RoutedEventArgs e)
        {
            labelMensaje.Content = "Adiós a todos";

            botonSaludar.IsEnabled = true;      // se puede volver a saludar
            botonDespedir.IsEnabled = false;
        }
    }
}
