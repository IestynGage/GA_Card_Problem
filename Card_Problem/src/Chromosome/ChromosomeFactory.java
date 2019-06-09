package Chromosome;

import java.util.ArrayList;
import java.util.Random;

/**
 * ChromosomeFactory
 * This class manages the population (array of chromosomes) and allows for the population to be manipulated
 *
 * @author Iestyn Gage
 * @date 08/06/2019
 * @version 1.0
 */
public class ChromosomeFactory {
    ///////////////////////
    //  Constants
    ///////////////////////
    private final static int POPULATION_SIZE = 30;
    ///////////////////////
    //  Variables
    ///////////////////////
    private int generation;
    private int chromosomeKilled = 0;
    ///////////////////////
    //  ArrayLists
    ///////////////////////
    private ArrayList<Chromosome> population = new ArrayList<Chromosome>();

    ///////////////////////
    //  Constructors
    ///////////////////////
    public ChromosomeFactory(){
        generation = 0;
        initializePopulation();
    }

    ///////////////////////
    //  Methods
    ///////////////////////

    /**
     * This creates the initial population, it uses POPULATION SIZE to create amount thats needed
     */
    private void initializePopulation(){
        for (int i = 0; i<POPULATION_SIZE; i++){
            Chromosome toBeAdded = new Chromosome();
            toBeAdded.initilize();
            population.add(toBeAdded);
        }
    }

    /**
     * This looks through all the solutions in the program to find the best Chromosome to return
     *
     * @return best Chromosome in population
     */
    public Chromosome getBestSolution(){
        Chromosome bestSolution = null;
        for (Chromosome possibleBest : population) {
            if(bestSolution==null || possibleBest.fitnessFunction() < bestSolution.fitnessFunction()){
                bestSolution = possibleBest;
            }
        }

        return bestSolution;
    }

    /**
     *This grabs a random solution from the population
     * @return a random Chromosome in the population
     */
    private Chromosome getRandomSolution(){
        Random random = new Random();

        return population.get(random.nextInt(population.size()-1));
    }

    /**
     * This returns the Chromosome Arraylist which represents population
     *
     * @return the population
     */
    public ArrayList<Chromosome> getPopulation(){

        return population;
    }

    /**
     * This returns the generation integer
     * @return gengeration integer
     */
    public int getGeneration(){
        return generation;
    }

    /**
     * This returns the chromosome discarded by ChromosomeFactory
     * @return
     */
    public int getChromosomeKilled() {
        return chromosomeKilled;
    }

    /**
     * This selects the top ten chromosomes, and ten random chromosomes for next generation. it then discards the remaining
     * chromosomes.
     */
    public void discard(){
       ArrayList<Chromosome> startOfNextPopulation = new ArrayList<Chromosome>();
       for(int i=0;i<=10; i++){
           Chromosome toBeAdded = getBestSolution();
           startOfNextPopulation.add(toBeAdded);
           population.remove(toBeAdded);
       }
        for(int i=0;i<=10; i++){
            Chromosome toBeAdded = getRandomSolution();
            startOfNextPopulation.add(toBeAdded);
            population.remove(toBeAdded);
        }

        chromosomeKilled += population.size();
        population = startOfNextPopulation;

    }

    /**
     * This recombines the remaing population untill it reaches the population size.
     */
    public void reCombination(){
        int amountNeededToBeAdded = POPULATION_SIZE - population.size();
        Random random = new Random();
        int originalPopulationSize = population.size()-1;
        for(int i=0;i<=amountNeededToBeAdded;i++){
            Chromosome parent1 = population.get(random.nextInt(originalPopulationSize));
            Chromosome parent2 = population.get(random.nextInt(originalPopulationSize));
            Chromosome toBeAdded = new Chromosome(parent1,parent2,true);

            population.add(toBeAdded);
        }
    }

    /**
     *  This discards the generation and then recombined them to make the next generation
     */
    public void nextGeneration(){
        generation++;
        discard();
        reCombination();

    }
}
