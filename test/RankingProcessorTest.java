
import org.junit.Test;
import static org.junit.Assert.*;

// it is just a unit test for RankingProcessor class
// if the ranking file changes, it is NECESSARY to change the expected result on this class methods
public class RankingProcessorTest {
        RankingProcessor rank1 = new RankingProcessor();
        
        public RankingProcessorTest() {
                rank1.readRanking();
                rank1.fillRankingInformation();
        }

        /**
         * Test of getNamesTop10 method, of class RankingProcessor.
         */
        @Test
        public void testGetNamesTop10() {
                System.out.println("getNamesTop10");
                int i = 4;
                String result = rank1.getNamesTop10(i);
                
                assertEquals(result, "Profile4");
                
        }

        /**
         * Test of getScoresTop10 method, of class RankingProcessor.
         */
        @Test
        public void testGetScoresTop10() {
                System.out.println("getScoresTop10");
                int i = 0;
                int result = (int) rank1.getScoresTop10(i);

                assertEquals(result, 0);
                
        }
        
}
