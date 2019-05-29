package Chromosome;

import java.util.ArrayList;
import java.util.Random;

public class ChromosomeFactory {

    private int generation;

    private ArrayList<Chromosome> population = new ArrayList<Chromosome>();
    private final static int POPULATION_SIZE = 30;

    public ChromosomeFactory(){
        generation = 0;
        initializePopulation();
    }

    private void initializePopulation(){
        for (int i = 0; i<POPULATION_SIZE; i++){
            Chromosome toBeAdded = new Chromosome();
            toBeAdded.initalize();
            population.add(toBeAdded);
        }
    }

    public Chromosome getBestSolution(){
        Chromosome bestSolution = null;
        for (Chromosome possibleBest : population) {
            if(bestSolution==null || possibleBest.fitnessFunction() < bestSolution.fitnessFunction()){
                bestSolution = possibleBest;
            }
        }

        return bestSolution;
    }

    private Chromosome getRandomSolution(){
        Random random = new Random();

        return population.get(random.nextInt(population.size()-1));
    }

    public ArrayList<Chromosome> getPopulation(){

        return population;
    }

    public int getGeneration(){
        return generation;
    }

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

        population = startOfNextPopulation;

    }

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

    public void nextGeneration(){
        generation++;
        discard();
        reCombination();

    }


}
