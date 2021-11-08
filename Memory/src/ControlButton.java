import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ControlButton implements ActionListener {

    private Model model;
    private Vue vue;
    JButton bout;
    JButton bout2;

    public ControlButton(Model model, Vue vue){
        this.model = model;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(!model.isInGame()){
            model.setInGame(true);
            vue.chrono.start();
        }



        bout = (JButton) e.getSource();

        if(model.doubleCarte[0] != null && model.doubleCarte[1] != null){

            if (model.doubleCarte[0].getDisabledIcon().toString().equals(model.doubleCarte[1].getDisabledIcon().toString())){
                model.incrementeDoubleCarteTrouve();
                model.doubleCarte[0].setEnabled(false);
                model.doubleCarte[1].setEnabled(false);
                model.doubleCarte[0] = null;
                model.doubleCarte[1] = null;

            }else {

                model.doubleCarte[0].setEnabled(true);
                model.doubleCarte[1].setEnabled(true);
                model.doubleCarte[0] = null;
                model.doubleCarte[1] = null;

                model.decrementeEssaie();

                vue.getTextEssais().setText("Essais = " + model.getEssaie() + "/" + model.essaieMax);
            }

        }

        if(model.doubleCarte[0] == null){
            bout.setEnabled(false);
            model.doubleCarte[0] = bout;
            if(model.getNbLigne() == 3 && bout.getName().equals("4")){
                model.decrementeEssaie();
                model.incrementeCartePiegeTrouve();
                model.doubleCarte[0] = null;
                vue.getTextEssais().setText("Essais = " + model.getEssaie() + "/" + model.essaieMax);
            }
            if(model.getNbLigne() == 5 && bout.getName().equals("12")){
                model.decrementeEssaie();
                model.incrementeCartePiegeTrouve();
                model.doubleCarte[0] = null;
                vue.getTextEssais().setText("Essais = " + model.getEssaie() + "/" + model.essaieMax);
            }
        }else if (model.doubleCarte[1] == null){
            bout.setEnabled(false);
            model.doubleCarte[1] = bout;

            if(model.getNbLigne() == 3 && bout.getName().equals("4")){
                model.decrementeEssaie();
                model.incrementeCartePiegeTrouve();
                model.doubleCarte[1] = null;
                vue.getTextEssais().setText("Essais = " + model.getEssaie() + "/" + model.essaieMax);
            }
            if(model.getNbLigne() == 5 && bout.getName().equals("12")){
                model.decrementeEssaie();
                model.incrementeCartePiegeTrouve();
                model.doubleCarte[1] = null;
                vue.getTextEssais().setText("Essais = " + model.getEssaie() + "/" + model.essaieMax);
            }

        }

        if(model.getEssaie() == 0){
            vue.chrono.stop();
            model.setInGame(false);
            vue.eteindreBoutton();
            vue.dialogError("Vous n'avez plus d'essaie restant. Game Over !");
        }



        if(model.getCarteTrouve() >= model.getNbCases()-2 && model.getEssaie() >0){ // Je fais -2 car je vérifie au prochain bouton si 2 cartes sont identiques
            vue.eteindreBoutton();

            vue.chrono.stop();
            String record = vue.getTimer();
            model.addRecord(record);
            System.out.println("timer = "+vue.getTimer());
            String [] records = model.getRecord();
            model.setInGame(false);
            vue.dialogError("Vous avez réussi, félicitations !\n Meilleurs records : \n1-" + records[0] + "\n2-" + records[1] + "\n3-" + records[2]);
        }









    }
}
