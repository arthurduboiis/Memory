import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class Model {
    private int essaie;
    public int essaieMax = 7;
    private int carteTrouve = 0 ;
    public JButton[] doubleCarte;
    private int nbLigne = 4;
    private int nbCases;
    private boolean inGame = false;
    

    

    private String[] bestScores = new String[3];


    private int nbImages;

    private int nbImagesMax = 13;

    public int [] order;



    ImageIcon[] listeImages;


    public Model(){
    }

    public void init(){
        setEssaie();
        setNbCases();
        setNbImages(getNbLigne());
        setCarteTrouve(0);
        chargerImage();
        setDoubleCarte();
        orderCarte();
    }

    public int getNbImagesMax(){
        return this.nbImagesMax;
    }



    public void chargerImage(){

        this.listeImages=new ImageIcon[getNbImages()];
        File[] listFiles = new File[getNbImagesMax()];
        
        
        try{
            File folder=new File("images/imagesJeu/");

            int cpt = 0;
            for (File fileEntry:folder.listFiles()){
                if(fileEntry.getName().startsWith("i")){
                    listFiles[cpt]=new File("images/imagesJeu/"+fileEntry.getName()) ;
                }
                cpt++;
            }

            Arrays.sort(listFiles);

            for (int i=0; i<getNbImages();i++) {


                if(listFiles[i].getName().startsWith("i")){
                    listeImages[i]=new ImageIcon((new ImageIcon("images/imagesJeu/"+listFiles[i].getName()).getImage().getScaledInstance(600/getNbLigne(),600/getNbLigne(), Image.SCALE_SMOOTH))) ;
                }


                if(getNbImages() == 5 ){
                    listeImages[getNbImages()-1] = new ImageIcon("images/imagesJeu/imagesFails.jpeg");
                }

                if(getNbImages() == 13 ){
                    listeImages[getNbImages()-1] = new ImageIcon("images/imagesJeu/imagesFails.jpeg");
                }

            }

        } catch(Exception e){
            e.printStackTrace();

        }

    }

    public void orderCarte(){

        this.order=new int[getNbCases()];
        Vector v=new Vector();
        for(int i=0;i<getNbCases();i++)
            v.add((int)(i%(getNbCases()/2)));
            if(getNbCases()==25){ // si on est en 5x5 je rajoute un chiffre tous seul pour mettre le piege
                v.add(12);
                v.removeElementAt(24);
            }
            if(getNbCases()==9){
                v.add(4);
                v.removeElementAt(8);
            }


        for(int i=0;i<getNbCases();i++){
            int rand= (int) (Math.random()*v.size());
            order[i]=(Integer)(v.elementAt(rand));
            v.removeElementAt(rand); }


        // Affichage de l'ordre pour réussir facilement lors des tests
        System.out.println();
            for(int j=0;j<getNbLigne();j++){

                System.out.print(order[j]+", ");
            }
            System.out.println("");
            for(int j=getNbLigne();j<getNbLigne()+getNbLigne();j++){
                System.out.print(order[j]+", ");
            }
            System.out.println();
            for(int j=getNbLigne()+getNbLigne();j<getNbLigne()+getNbLigne()+getNbLigne();j++){
                System.out.print(order[j]+", ");
            }
        if (getNbLigne() == 4) {
            System.out.println();
            for(int j=getNbLigne()+getNbLigne()+getNbLigne();j<getNbLigne()+getNbLigne()+getNbLigne()+getNbLigne();j++){
                System.out.print(order[j]+", ");
            }
        }
        if (getNbLigne() == 5){
            System.out.println();
            for(int j=getNbLigne()+getNbLigne()+getNbLigne();j<getNbLigne()+getNbLigne()+getNbLigne()+getNbLigne();j++){
                System.out.print(order[j]+", ");
            }
            System.out.println();
            for(int j=getNbLigne()+getNbLigne()+getNbLigne()+getNbLigne();j<getNbLigne()+getNbLigne()+getNbLigne()+getNbLigne()+getNbLigne();j++){
                System.out.print(order[j]+", ");
            }
        }

        // fin de l'affichage

        

    }

    public String[] getRecord(){
        getFichierRecord();
        return bestScores;
    }

    public void getFichierRecord(){

        try{
            int cpt=0 ;
            String s;
            BufferedReader br;
            if(getNbLigne()==3){
                br = new BufferedReader(new FileReader("records/records3x3.txt"));
            } else if (getNbLigne() == 4) {
                br = new BufferedReader(new FileReader("records/records4x4.txt"));
            } else {
                br = new BufferedReader(new FileReader("records/records5x5.txt"));
            }
            while (( s= br.readLine()) != null) {
                this.bestScores[cpt]= s;
                cpt++;
            }
            br.close();

        }

        catch (Exception e){ e.printStackTrace(); }
    }

    public void addRecord(String record){
            try{

                getFichierRecord();
                record = record.replace(',','.');



                if(Float.parseFloat(record) < Float.parseFloat(bestScores[0])){
                    System.out.println("je rentre pas ici");
                    String ancienPremier = bestScores[0];
                    bestScores[0] =  record;
                    String ancienDeuxieme = bestScores[1];
                    bestScores[1] = ancienPremier;
                    bestScores[2] = ancienDeuxieme;
                }
                else if(Float.parseFloat(record) > Float.parseFloat(bestScores[0]) && Float.parseFloat(record) < Float.parseFloat(bestScores[1])){
                    String ancienDeuxieme = bestScores[1];
                    bestScores[1] = record;
                    bestScores[2] = ancienDeuxieme;
                }
                else if(Float.parseFloat(record) > Float.parseFloat(bestScores[1]) && Float.parseFloat(record) < Float.parseFloat(bestScores[2])){
                    String ancienTroisieme = bestScores[2];
                    bestScores[2] = record;
                }
                BufferedWriter bw;
                if(getNbLigne()==3){
                    bw = new BufferedWriter(new FileWriter("records/records3x3.txt"));
                } else if (getNbLigne() == 4) {
                    bw = new BufferedWriter(new FileWriter("records/records4x4.txt"));
                } else {
                    bw = new BufferedWriter(new FileWriter("records/records5x5.txt"));
                }
                bw.write(""+ bestScores[0]);
                bw.newLine();
                bw.write(""+ bestScores[1]);
                bw.newLine();
                bw.write(""+ bestScores[2]);
                bw.newLine();
                bw.close();
            }catch(Exception e){ e.printStackTrace(); }

    }
    

    public void setDoubleCarte(){
        doubleCarte = new JButton[2];
    }


    public int getEssaie() {
        return this.essaie;
    }

    public void setEssaie() {
        switch (nbLigne) {
            case 3 -> {
                this.essaie = 3;
                this.essaieMax = 3;
            }
            case 4 -> {
                this.essaie = 6;
                this.essaieMax = 6;
            }
            case 5 -> {
                this.essaie = 12;
                this.essaieMax = 12;
            }
        }
    }


    public void decrementeEssaie(){
        this.essaie--;
    }


    public void setCarteTrouve(int carteTrouve) {
        this.carteTrouve = carteTrouve;
    }
    public int getCarteTrouve(){
        return this.carteTrouve;
    }
    public void incrementeDoubleCarteTrouve(){
        this.carteTrouve = this.carteTrouve+2;
    }
    public void incrementeCartePiegeTrouve(){
        this.carteTrouve++;
    }

    public int getNbLigne() {
        return nbLigne;
    }

    public void setNbLigne(int nbLigne) {
        this.nbLigne = nbLigne;
    }
    public ImageIcon[] getListeImages() {
        return listeImages;
    }

    public void setNbImages(int nbLigne){
        switch (nbLigne) {
            case 3 -> this.nbImages = 5;
            case 4 -> this.nbImages = 8;
            case 5 -> this.nbImages = 13;
        }
    }

    public int getNbImages() {
        return nbImages;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public void setNbCases() {
         this.nbCases = nbLigne*nbLigne;
    }

    public int getNbCases(){
        return this.nbCases;
    }


    
}
