iimport Chromosome.Chromosome;
import Chromosome.ChromosomeFactory;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main
 * This file allows the user to maniuplate the population by entering input into the terminal.
 *
 * @author Iestyn Gage
 * @date 08/06/2019
 * @version 1.0
 */
public class Main {

    ///////////////////////
    //  Objects
    ///////////////////////
    ChromosomeFactory population = new ChromosomeFactory();

    ///////////////////////
    //  Methods
    ///////////////////////

    /**
     * This method prints several strings to display the 5 options of the program
     */
    public void menuOptions(){
        System.out.println("---- Menu ----");
        System.out.println("1 - Next 10 Generationn");
        System.out.println("2 - Get Best Solution");
        System.out.println("3 - Get All Solution");
        System.out.println("4 - Get Population details");
        System.out.println("5 - Quit");
        System.out.println("----=======----");
    }

    /**
     * This runs a loop that prints the menu, then interprets the input, then call another method to preform the menu action.
     */
    public void menuLoop(){
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        int option;
        while(!quit){
            menuOptions();
            option = scanner.nextInt();
            switch(option){
                case 1:
                    nextGeneration();
                    break;
                case 2:
                    getBest();
                    break;

                case 3:
                    getAll();
                    break;
                case 4:
                    getPopulationDetails();
                    break;
                case 5:
                    System.out.println("Program closing");
                    quit=true;
                    break;

                default:
                    menuLoop();
                    break;
            }

        }

    }

    /**
     * This runs the population next generation method ten times
     */
    private void nextGeneration(){
        for(int i=0;i<10;i++){
            population.nextGeneration();
        }

    }

    /**
     * This gets the best solution from the population, it then prints the best solution
     */
    private void getBest(){
        Chromosome best = population.getBestSolution();
        System.out.println("----------------");
        System.out.println(best.toString());
        System.out.println("----------------");
    }

    /**
     * This gets all the chromosome that are in the population
     */
    private void getAll(){
        ArrayList<Chromosome> thePopulation = population.getPopulation();
        for (Chromosome eachChromosome :thePopulation) {
            System.out.println("----------------");
            System.out.println(eachChromosome.toString());
            System.out.println("----------------");
        }
    }

    /**
     * This prints the amount of population
     */
    private void getPopulationDetails(){
        int generation = population.getGeneration();
        System.out.println("----------------");
        System.out.println("The Population Number is: "+ generation);
        Chromosome theBest = population.getBestSolution();
        if(theBest.fitnessFunction()==0){
            System.out.println("Has converange been reached: True");
        } else {
            System.out.println("Has converange been reached: False");
        }
        System.out.println("Chromosome Killed: " + population.getChromosomeKilled());
        System.out.println("----------------");
    }

    /**
     * This runs the application
     * @param args
     */
    public static void main(String args[]){
        Main application = new Main();
        application.menuLoop();

    }
}
