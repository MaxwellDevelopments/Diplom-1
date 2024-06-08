import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Bun;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.is;

class BunTests {

    private Bun bun;



    private final String EXPECTED_BUN_NAME = Global.getFakeName();
    private final float EXPECTED_PRICE = Global.getFakePrice();

    @BeforeEach
    void setUp() {
        bun = new Bun(EXPECTED_BUN_NAME, EXPECTED_PRICE);
    }

    @Test
    void getNameTest() {
        String errorMessage = "Не совпадает имя переданное в конструктор и возвращенное геттером";
        String actual = bun.getName();
        MatcherAssert.assertThat(
                errorMessage,
                actual,
                is(EXPECTED_BUN_NAME)
        );
    }


    @Test
    void getPriceTest() {
        String errorMessage = "Не совпадает цена переданная в конструктор и возвращенная геттером";
        float actual = bun.getPrice();
        MatcherAssert.assertThat(
                errorMessage,
                actual,
                is(EXPECTED_PRICE)
        );
    }

}
