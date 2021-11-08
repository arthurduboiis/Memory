import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenu implements ActionListener{

    private Model model;
    private Vue vue;

    public ControlMenu(Model model,Vue vue){
        this.model = model;
        this.vue = vue;

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vue.taille3){
            model.setNbLigne(3);
            model.setInGame(false);
            //model.init();
            vue.getTextEssais().setText("Essais : "+ model.getEssaie() + "/" + model.essaieMax);
            vue.newParty();
        }
        if(e.getSource() == vue.taille4){
            model.setNbLigne(4);
            model.setInGame(false);
            vue.getTextEssais().setText("Essais : "+ model.getEssaie() + "/" + model.essaieMax);
            vue.newParty();
        }
        if(e.getSource() == vue.taille5){
            model.setNbLigne(5);
            model.setInGame(false);
            vue.getTextEssais().setText("Essais : "+ model.getEssaie() + "/" + model.essaieMax);
            vue.newParty();
        }
        if(e.getSource() == vue.nouvellePartie){
            model.setInGame(false);
            vue.getTextEssais().setText("Essais : "+ model.getEssaie() + "/" + model.essaieMax);
            vue.newParty();
        }
        if(e.getSource() == vue.records){
            String [] records = model.getRecord();
            vue.dialogError("Meilleurs scores : \n1-" + records[0] + "\n2-" + records[1] + "\n3-" + records[2] + "\n");
        }
    }

}
