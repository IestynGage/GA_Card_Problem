package Chromosome;

import java.util.Random;

public class Chromosome {

    private PileType[] helix;
    private static final int HELIX_SIZE = 10;

    public Chromosome(Chromosome parent1, Chromosome parent2,Boolean mutation){
        helix = new PileType[10];
        setHelixPart(0,2,parent1.getHelix());
        setHelixPart(3,6,parent2.getHelix());
        setHelixPart(7,9,parent1.getHelix());
        if(mutation==true){
            mutate();
        }

    }

    public Chromosome(PileType[] theHelix){
        helix = theHelix;

    }

    public Chromosome(){

    }

    public void initalize(){
        helix = new PileType[10];
        Random random = new Random();

        for(int i=0;i<10;i++){
            int randomChoice = random.nextInt(2);
            if(randomChoice==0){
                helix[i] = PileType.Addition;
            }
            else if(randomChoice==1){
                helix[i] = PileType.Multiplication;
            }
            else{
                helix[i] = null;
            }
        }
    }

    private void setHelixPart(int startPositiion, int endPosition, PileType[] parent){
        for(int arrayPosition = startPositiion; arrayPosition<=endPosition; arrayPosition++){
            helix[arrayPosition] = parent[arrayPosition];
        }
    }


    public PileType[] getHelix(){
        return helix;
    }

    public int getMultiplication(){
        int output = 1;
        for (int i = 0;i< HELIX_SIZE;i++) {
            if(helix[i] == PileType.Multiplication){
                output = output * (i+1);
            }
        }
        return output;
    }

    public int getAddition(){
        int output = 0;
        for (int i = 0;i< HELIX_SIZE;i++) {
            if(helix[i] == PileType.Addition){
                output += (i+1);
            }
        }
        return output;
    }

    public void mutate(){
        Random random = new Random();

        int amountRandomise = random.nextInt(HELIX_SIZE-1);
        for(int i=0;i<=amountRandomise;i++){
            int randomInt = random.nextInt(HELIX_SIZE-1);

            if(helix[randomInt]==PileType.Addition){
                helix[randomInt]=PileType.Multiplication;
            }else {
                helix[randomInt]=PileType.Addition;
            }
        }

    }

    public int fitnessFunction(){
        int output;
        int multiplicationPile = (360 - getMultiplication())/10;
        if (multiplicationPile<0){
            multiplicationPile = multiplicationPile*-1;
        }
        int additionPile = 36 - getAddition();
        if(additionPile<0){
            additionPile = additionPile*-1;
        }

        output =multiplicationPile+additionPile;
        return output;
    }

    public String helixToString(){
        StringBuilder output = new StringBuilder();

        for (PileType helixDigit:helix) {
            output.append(helixDigit.toString());
            output.append(", ");
        }

        return output.toString();
    }
    public String toString(){
        String output = new String();
        output = "Helix: " + helixToString() + "\n" + "Multiplication: " + getMultiplication() + " Addition: " + getAddition() + " Fitness Score:" + fitnessFunction();

        return output;
    }
}
