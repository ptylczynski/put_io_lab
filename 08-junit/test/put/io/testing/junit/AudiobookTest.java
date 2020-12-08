package put.io.testing.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import put.io.testing.audiobooks.Audiobook;
import put.io.testing.audiobooks.AudiobookPriceCalculator;
import put.io.testing.audiobooks.Customer;

import static org.junit.Assert.assertEquals;

public class AudiobookTest {

    private AudiobookPriceCalculator audiobook;

    @Before
    public void init(){
        this.audiobook = new AudiobookPriceCalculator();
    }

    @Test
    public void testBasic(){
        assertEquals(
            audiobook.calculate(
                    new Customer("safsaf", Customer.LoyaltyLevel.GOLD, true),
                    new Audiobook("dsfsd", 32)
                ),
                0,
                0.1
            );
    }

    @Test
    public void test_isSubscriber(){
        assertEquals(
                audiobook.calculate(
                        new Customer("safsaf", Customer.LoyaltyLevel.GOLD, true),
                        new Audiobook("dsfsd", 32)
                ),
                0,
                0.1
        );
    }

    @Test
    public void test_isGold(){
        Audiobook a = new Audiobook("dsfsd", 32);
        assertEquals(
                audiobook.calculate(
                        new Customer("safsaf", Customer.LoyaltyLevel.GOLD, false),
                        a
                ),
                0.8 * a.getStartingPrice(),
                0.1
        );
    }

    @Test
    public void test_isSilver(){
        Audiobook a = new Audiobook("dsfsd", 32);
        assertEquals(
                audiobook.calculate(
                        new Customer("safsaf", Customer.LoyaltyLevel.SILVER, false),
                        a
                ),
                0.9 * a.getStartingPrice(),
                0.1
        );
    }

}
