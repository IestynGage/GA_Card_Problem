import Chromosome.Chromosome;
import Chromosome.ChromosomeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChromosomeFactoryTest {

    @Test
    void testPopulationCreation(){
        ChromosomeFactory chromosomeFactory = new ChromosomeFactory();

        for (Chromosome a: chromosomeFactory.getPopulation()) {
                if(a==null){
                    Assertions.fail();
                }
        }

    }
}
