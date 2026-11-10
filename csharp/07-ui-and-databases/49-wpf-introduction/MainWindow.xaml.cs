using System.Windows;

namespace WpfIntroduction
{
    // Código de la ventana (el "code-behind" de MainWindow.xaml).
    // "partial" significa que la clase se completa con otra parte generada a partir del XAML.
    // Window es la clase de WPF que representa una ventana: MainWindow hereda de ella (lección 38).
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            // Carga los elementos definidos en el XAML y los conecta con este código.
            // Hay que llamarlo siempre primero.
            InitializeComponent();
        }

        // Se ejecuta cuando se pulsa el botón "aceptar" (el nombre se indica en Click="Aceptar_Click" del XAML)
        private void Aceptar_Click(object sender, RoutedEventArgs e)
        {
            // Los elementos con x:Name se usan por su nombre, como variables de la clase
            mensaje.Text = "Has pulsado Aceptar";
            aceptar.IsEnabled = false;      // el botón se desactiva
        }
    }
}
