using System.Globalization;
using System.Windows;

namespace WpfCheckboxRadiobuttons
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        // Botón Ordenar: calcula el total de lo que se ha seleccionado
        private void BOrdenar_Click(object sender, RoutedEventArgs e)
        {
            // IsChecked: true si el CheckBox/RadioButton está marcado. Es un bool? (true, false o null),
            // por eso se compara con true / false.
            // Regla de negocio: no se puede ordenar sin pizza (una bebida sola no vale)
            if (ck01.IsChecked == false && ck02.IsChecked == false && ck03.IsChecked == false)
            {
                lTotal.Content = "Debe seleccionar una pizza con la orden";
            }
            else
            {
                double total = 0.0;

                // Selección de pizzas: se suman todas las marcadas (pueden ser una, dos o las tres)
                if (ck01.IsChecked == true) total += 150.00;
                if (ck02.IsChecked == true) total += 250.00;
                if (ck03.IsChecked == true) total += 200.00;

                // Selección de bebidas: solo una puede estar marcada (RadioButton)
                if (rb01.IsChecked == true) total += 30.00;
                if (rb02.IsChecked == true) total += 30.00;
                if (rb03.IsChecked == true) total += 25.00;

                // F2: dos decimales. InvariantCulture: siempre con punto, sea cual sea el idioma del sistema
                lTotal.Content = "$" + total.ToString("F2", CultureInfo.InvariantCulture);
            }
        }

        // Botón Cancelar: desmarca todo y limpia el total
        private void BCancelar_Click(object sender, RoutedEventArgs e)
        {
            ck01.IsChecked = false;
            ck02.IsChecked = false;
            ck03.IsChecked = false;

            rb01.IsChecked = false;
            rb02.IsChecked = false;
            rb03.IsChecked = false;

            lTotal.Content = string.Empty;      // string.Empty: el texto vacío ""
        }
    }
}
