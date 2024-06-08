import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.hamcrest.CoreMatchers.is;

class IngredientsTests {

    @Mock
    private IngredientType mockIngredientType;

    private Ingredient ingredient;

    String INGREDIENT_NAME = Global.getFakeIngredient();
    float INGREDIENT_PRICE = Global.getFakePrice();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredient = new Ingredient(mockIngredientType, INGREDIENT_NAME, INGREDIENT_PRICE);
    }

    @Test
    void testGetPrice() {
        float delta = 0.0000001f;
        String errorMessage = "Возвращенный геттером ingredientPrice не соответствует переданному";

        getterTest(
                errorMessage,
                Math.abs(ingredient.getPrice() - INGREDIENT_PRICE) < delta,
                true
        );
    }

    @Test
    void testGetName() {
        String errorMessage = "Возвращенный геттером ingredientName не соответствует переданному";
        getterTest(errorMessage, ingredient.getName(), INGREDIENT_NAME);
    }

    @Test
    void testGetType() {
        String errorMessage = "Возвращенный геттером IngredientType не соответствует переданному";
        getterTest(errorMessage, ingredient.getType(), mockIngredientType);
    }

    private <A, E> void getterTest(String errorMesage, A actual, E expected) {
        MatcherAssert.assertThat(
                errorMesage,
                actual,
                is(expected));
    }
}