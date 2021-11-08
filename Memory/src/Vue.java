import javax.swing.*;
import java.awt.*;

public class Vue extends JFrame {


    private final int HEIGHT_FENETRE = 720;
    private final int WIDTH_FENETRE = 600;



    private Model model;

    ControlMenu menuPrincipal;
    ControlButton controlButton;

    JPanel general = new JPanel();

    //panel grille de jeu
    JPanel grid;

    //boutton de jeu
    JButton[] buttons;

    Chrono chrono;

    // Panel temps
    JPanel time;
    JLabel textTemps;
    JLabel timer;

    // Panel essais
    JPanel essais;
    JLabel textEssais;


    // Menu
    JMenuBar menu;
    JMenu options, tailles;
    JMenuItem nouvellePartie, records, taille3,taille4,taille5;

    public Vue(Model model){
        this.model = model;


        initAttribut();


        setTitle("Memories");
        setSize(WIDTH_FENETRE, HEIGHT_FENETRE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public void initAttribut(){
        model.init();
        menuPrincipal = new ControlMenu(model,this);


        creerMenu();

        time = new JPanel();

        textTemps = new JLabel("Temps = ");
        timer = new JLabel();
        this.chrono = new Chrono(timer);
        time.add(textTemps);
        time.add(timer);
        general.add(time);



        creerGrille();



        essais = new JPanel();


        textEssais = new JLabel("Essais : " + model.getEssaie() + "/" + model.getEssaie());
        essais.add(textEssais);




        general.add(essais);

        setContentPane(general);


    }


    public void creerGrille(){
        controlButton =new ControlButton(model, this);



        grid = new JPanel(new GridLayout(model.getNbLigne(),model.getNbLigne()));

        Icon images = new ImageIcon((new ImageIcon("images/DosCarte.jpeg").getImage().getScaledInstance(WIDTH_FENETRE/model.getNbLigne(),WIDTH_FENETRE/model.getNbLigne(),Image.SCALE_SMOOTH)));


        buttons = new JButton[model.getNbCases()];

        for(int i=0;i<model.getNbCases();i++){
            //ImageIcon disabledIcon = new ImageIcon(String.valueOf(model.listeImages[model.order[i]]));
            ImageIcon disabledIcon = model.listeImages[model.order[i]];
            buttons[i] = new JButton(images);
            buttons[i].setName(String.valueOf(model.order[i]));
            buttons[i].addActionListener(controlButton);
            buttons[i].setPreferredSize(new Dimension(WIDTH_FENETRE/ model.getNbLigne(),WIDTH_FENETRE/model.getNbLigne()));
            buttons[i].setDisabledIcon(disabledIcon);
            grid.add(buttons[i]);

        }


        general.add(grid);
    }

    public void eteindreBoutton(){
        for(int i=0; i<buttons.length;i++){
            buttons[i].setEnabled(false);
        }
    }

    public void creerMenu(){
        menu = new JMenuBar();
        options = new JMenu("Options");


        nouvellePartie = new JMenuItem("Nouvelle Partie");
        nouvellePartie.addActionListener(menuPrincipal);

        records = new JMenuItem("Records");
        records.addActionListener(menuPrincipal);

        tailles = new JMenu("Tailles");
        taille3 = new JMenuItem("3x3");
        taille4 = new JMenuItem("4x4");
        taille5 = new JMenuItem("5x5");

        taille3.addActionListener(menuPrincipal);
        taille4.addActionListener(menuPrincipal);
        taille5.addActionListener(menuPrincipal);

        tailles.add(taille3);
        tailles.add(taille4);
        tailles.add(taille5);


        options.add(nouvellePartie);
        options.add(records);
        options.addSeparator();
        options.add(tailles);



        menu.add(options);

        this.setJMenuBar(menu);
    }

    public void newParty(){
        this.getContentPane().removeAll();
        //this.dispose();
        this.initAttribut();
        this.setVisible(true);

    }

    public void dialogError(String message) {

        JOptionPane d = new JOptionPane();
        d.showMessageDialog( this, message, "Message", JOptionPane.INFORMATION_MESSAGE );
        JDialog fenErr = d.createDialog(this, "Message");

    }
    public JLabel getTextEssais(){
        return this.textEssais;
    }

    public String getTimer(){
        return this.timer.getText();
    }

    public int getHeightFenetre() {
        return HEIGHT_FENETRE;
    }

    public int getWidthFenetre() {
        return WIDTH_FENETRE;
    }




}
