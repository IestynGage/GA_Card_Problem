import Chromosome.Chromosome;
import Chromosome.ChromosomeFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    ChromosomeFactory population = new ChromosomeFactory();

    public void menuOptions(){
        System.out.println("---- Menu ----");
        System.out.println("1 - Next 10 Generationn");
        System.out.println("2 - Get Best Solution");
        System.out.println("3 - Get All Solution");
        System.out.println("4 - Get Population details");
        System.out.println("5 - Quit");
        System.out.println("----=======----");
    }

    public void menuLoop() throws IOException {
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

    private void nextGeneration(){
        for(int i=0;i<10;i++){
            population.nextGeneration();
        }

    }

    private void getBest(){
        Chromosome best = population.getBestSolution();
        System.out.println("----------------");
        System.out.println(best.toString());
        System.out.println("----------------");
    }

    private void getAll(){
        ArrayList<Chromosome> thePopulation = population.getPopulation();
        for (Chromosome eachChromosome :thePopulation) {
            System.out.println("----------------");
            System.out.println(eachChromosome.toString());
            System.out.println("----------------");
        }
    }

    private void getPopulationDetails(){
        int generation = population.getGeneration();
        System.out.println("The Population Number is: "+ generation);
    }

    public static void main(String args[]){
        Main application = new Main();
        try {
            application.menuLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
