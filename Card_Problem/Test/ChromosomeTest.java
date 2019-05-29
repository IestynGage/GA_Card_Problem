import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Chromosome.*;
import java.util.ArrayList;

public class ChromosomeTest {

    private ArrayList<Chromosome> createSetChromosome(){
        ArrayList<Chromosome> output = new ArrayList<Chromosome>();

        PileType[] mixHelix = {PileType.Addition,PileType.Addition,PileType.Addition,
                                    PileType.Addition,PileType.Addition,PileType.Multiplication,PileType.Multiplication,
                                    PileType.Multiplication,PileType.Multiplication,PileType.Multiplication,};

        PileType[] multiplicationHelix = {PileType.Multiplication,PileType.Multiplication,PileType.Multiplication,
                                        PileType.Multiplication,PileType.Multiplication,PileType.Multiplication,PileType.Multiplication,
                                        PileType.Multiplication,PileType.Multiplication,PileType.Multiplication,};

        PileType[] addHelix = {PileType.Addition,PileType.Addition,PileType.Addition,
                                PileType.Addition,PileType.Addition,PileType.Addition,PileType.Addition,
                                PileType.Addition,PileType.Addition,PileType.Addition,};

        Chromosome mixChromosome = new Chromosome(mixHelix);
        Chromosome multplicationChromosome = new Chromosome(multiplicationHelix);
        Chromosome addChromosome = new Chromosome(addHelix);

        output.add(0,mixChromosome);
        output.add(1,multplicationChromosome);
        output.add(2,addChromosome);

        return output;
    }

    @Test
    void testGetMultiplication() {
        Chromosome actualData = createSetChromosome().get(0);

        int expectedData = 30240;

        Assertions.assertEquals(expectedData,actualData.getMultiplication());
    }

    @Test
    void testGetAddition(){
        Chromosome actualData = createSetChromosome().get(0);

        int expectedData = 15;

        Assertions.assertEquals(expectedData,actualData.getAddition());

    }

    @Test
    void testTwoPointCrossover(){
        PileType[] exptectedHelix = {PileType.Multiplication,PileType.Multiplication,PileType.Multiplication,
                                     PileType.Addition,PileType.Addition,PileType.Addition,PileType.Addition,
                                     PileType.Multiplication,PileType.Multiplication,PileType.Multiplication,};
        Chromosome expectedChromosome = new Chromosome(exptectedHelix);

        Chromosome parent1 = createSetChromosome().get(1);
        Chromosome parent2 = createSetChromosome().get(2);

        Chromosome actualData = new Chromosome(parent1,parent2,false);

        Assertions.assertEquals(actualData.helixToString(),expectedChromosome.helixToString());
    }

    @Test
    void testFitnessFunction(){
        Chromosome actualChromosome = createSetChromosome().get(2);
        int addition = actualChromosome.getAddition();
        addition = 36 - addition;
        if(addition<0){
            addition = addition*-1;
        }
        int multiplication = actualChromosome.getMultiplication();
        multiplication = (360 - multiplication)/10;
        if(multiplication<0){
            multiplication = multiplication*-1;
        }

        int expectedFitnessScore = addition + multiplication;

        int actualFitnessScore = actualChromosome.fitnessFunction();

        Assertions.assertEquals(actualFitnessScore,expectedFitnessScore);
    }
}
