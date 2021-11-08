import javax.swing.*;

public class Appli extends JPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Appli::begin);
    }

    public static void begin(){
        Model model =new Model();
        Vue vue = new Vue(model);

    }
}
