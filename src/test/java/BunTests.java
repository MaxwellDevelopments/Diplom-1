import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Bun;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.is;

class BunTests {

    private Bun bun;



    private final String BUN_NAME = Global.getFakeName();
    private final float PRICE = Global.getFakePrice();

    @BeforeEach
    void setUp() {
        bun = new Bun(BUN_NAME, PRICE);
    }

    @Test
    void getNameTest() {
        String errorMessage = "Не совпадает имя переданное в конструктор и возвращенное геттером";
        getterTest(errorMessage, bun.getPrice(), PRICE);
    }

    @Test
    void getPriceTest() {
        String errorMessage = "Не совпадает цена переданная в конструктор и возвращенная геттером";
        getterTest(errorMessage, bun.getPrice(), PRICE);
    }

    private <A, E> void getterTest(String errorMesage, A actual, E expected) {
        MatcherAssert.assertThat(
                errorMesage,
                actual,
                is(expected));
    }
}
