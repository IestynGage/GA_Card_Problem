package Chromosome;

import java.util.Random;

/**
 * Chromosome
 * This class represents one possible solution to the card problem
 * Each position in the pileType Array determines what card number is in each pile.
 * (e.g. pos 0 = Addition means card 1 is in Addition Pile)
 *
 * @author Iestyn Gage
 * @date 08/06/2019
 * @version 1.0
 */
public class Chromosome {
    ///////////////////////
    //  Constants
    ///////////////////////
    private static final int HELIX_SIZE = 10;

    ///////////////////////
    //  Arrays
    ///////////////////////
    private PileType[] helix;

    ///////////////////////
    //  Constructors
    ///////////////////////

    /**
     * This construcs a new chromosome with 2 parents with a Two-point crossover
     *
     * @param parent1
     * @param parent2
     * @param mutation
     */
    public Chromosome(Chromosome parent1, Chromosome parent2,Boolean mutation){
        helix = new PileType[10];
        setHelixPart(0,2,parent1.getHelix());
        setHelixPart(3,6,parent2.getHelix());
        setHelixPart(7,9,parent1.getHelix());
        if(mutation==true){
            mutate();
        }

    }

    /**
     * This construcs a Chromosome with specific data
     * @param theHelix
     */
    public Chromosome(PileType[] theHelix){
        helix = theHelix;

    }

    /**
     * This construcs a empty chromosome
     */
    public Chromosome(){

    }

    ///////////////////////
    //  Methods
    ///////////////////////

    /**
     * This fills in helix data with random data
     */
    public void initilize(){
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

    /**
     * This copys another part of Chromosome to this chromosome
     *
     * @param startPositiion
     * @param endPosition
     * @param parent
     */
    private void setHelixPart(int startPositiion, int endPosition, PileType[] parent){
        for(int arrayPosition = startPositiion; arrayPosition<=endPosition; arrayPosition++){
            helix[arrayPosition] = parent[arrayPosition];
        }
    }

    /**
     * This returns the PileType Array
     *
     * @return
     */
    public PileType[] getHelix(){
        return helix;
    }

    /**
     * This gets the total multiplication number from multplication pile
     * @return integer value from all multplication pile cards multilpied togeather
     */
    public int getMultiplication(){
        int output = 1;
        for (int i = 0;i< HELIX_SIZE;i++) {
            if(helix[i] == PileType.Multiplication){
                output = output * (i+1);
            }
        }
        return output;
    }

    /**
     * This gets the total Addition number from Addition pile
     * @return integer value from all Addition pile cards added togeather
     */
    public int getAddition(){
        int output = 0;
        for (int i = 0;i< HELIX_SIZE;i++) {
            if(helix[i] == PileType.Addition){
                output += (i+1);
            }
        }
        return output;
    }

    /**
     * This selects random amount of random position in the array to swap the PileTypes
     */
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

    /**
     * This calculates the score from the chromosome.
     * @return the Score of chromosome
     */
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

    /**
     * This gets PileType and returns array in string format
     * @return PileType array in Scring format
     */
    public String helixToString(){
        StringBuilder output = new StringBuilder();

        for (PileType helixDigit:helix) {
            output.append(helixDigit.toString());
            output.append(", ");
        }

        return output.toString();
    }

    /**
     * This returns all information in the chromosome
     *
     * @return String of all the information in the Chromosome
     */
    public String toString(){
        String output = new String();
        output = "Helix: " + helixToString() + "\n" + "Multiplication: " + getMultiplication() + " Addition: " + getAddition() + " Fitness Score:" + fitnessFunction();

        return output;
    }
}
