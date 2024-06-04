import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import org.hamcrest.MatcherAssert;
import praktikum.IngredientType;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTests {
    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    IngredientType ing1Type;

    @Mock
    IngredientType ing2Type;

    @Spy
    Burger burger;

    @Spy
    ArrayList<Ingredient> spyList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        burger.ingredients = spyList;
    }

    @Test
    void testSetBuns() {
        burger.setBuns(mockBun);

        MatcherAssert.assertThat(
                burger.bun,
                is(mockBun)
        );
    }

    @Test
    void testAddIngredient() {
        int sizeOfTheIngListAfterFirstAdd = 1;
        int sizeOfTheIngListAfterSecondAdd = 2;

        burger.addIngredient(mockIngredient1);

        MatcherAssert.assertThat(
                burger.ingredients.size(),
                is(sizeOfTheIngListAfterFirstAdd)
        );

        burger.addIngredient(mockIngredient2);

        MatcherAssert.assertThat(burger.ingredients.get(0), is(mockIngredient1));
        MatcherAssert.assertThat(burger.ingredients.size(), is(sizeOfTheIngListAfterSecondAdd));
        MatcherAssert.assertThat(burger.ingredients.get(1), is(mockIngredient2));

        verify(spyList, Mockito.times(1)).add(mockIngredient1);
        verify(spyList, Mockito.times(1)).add(mockIngredient2);
    }

    @Test
    void testRemoveIngredient() {
        int ingredientToRemoveInd = 0;

        burger.addIngredient(mockIngredient1);
        burger.removeIngredient(ingredientToRemoveInd);

        MatcherAssert.assertThat(burger.ingredients.size(), is(0));

        verify(spyList, Mockito.times(1)).remove(ingredientToRemoveInd);
    }

    @Test
    void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(0, 1);

        MatcherAssert.assertThat(burger.ingredients.get(1), is(mockIngredient1));
        MatcherAssert.assertThat(burger.ingredients.get(0), is(mockIngredient2));

        verify(spyList).add(1, mockIngredient1);
        verify(spyList).remove(0);
    }

    @Test
    void testGetPrice() {
        float bunPrice = Global.getFakePrice();
        float ingredient1Price = Global.getFakePrice();
        float ingredient2Price = Global.getFakePrice();
        float total = 2*bunPrice + ingredient1Price + ingredient2Price;

        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockIngredient1.getPrice()).thenReturn(ingredient1Price);
        when(mockIngredient2.getPrice()).thenReturn(ingredient2Price);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float price = burger.getPrice();
        MatcherAssert.assertThat(price, is(total));

        verify(mockBun, Mockito.times(1)).getPrice();
        verify(mockIngredient1, Mockito.times(1)).getPrice();
        verify(mockIngredient2, Mockito.times(1)).getPrice();
    }



    @Test
    void testGetReceipt() {
        String bunName = Global.getFakeIngredient();
        String sauce = Global.getFakeSauce();
        String filling = Global.getFakeIngredient();
        float bunPrice = Global.getFakePrice();
        float ingredient1Price = Global.getFakePrice();
        String ingredient1Type = Global.getFakeIngredient();
        float ingredient2Price = Global.getFakePrice();
        String ingredient2Type = Global.getFakeIngredient();

        when(mockBun.getName()).thenReturn(bunName);
        when(mockBun.getPrice()).thenReturn(bunPrice);

        when(mockIngredient1.getName()).thenReturn(sauce);
        when(ing1Type.toString()).thenReturn(ingredient1Type);
        when(mockIngredient1.getType()).thenReturn(ing1Type);
        when(mockIngredient1.getPrice()).thenReturn(ingredient1Price);

        when(mockIngredient2.getName()).thenReturn(filling);
        when(ing2Type.toString()).thenReturn(ingredient2Type);
        when(mockIngredient2.getType()).thenReturn(ing2Type);
        when(mockIngredient2.getPrice()).thenReturn(ingredient2Price);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String expectedReceipt = String.format(
            "(==== %s ====)\r\n" +
            "= %s %s =\r\n=" +
            " %s %s =\r\n" +
            "(==== %s ====)\r\n\r\n" +
            "Price: %f\r\n",

            bunName,
            ingredient1Type.toLowerCase(),
            sauce,
            ingredient2Type.toLowerCase(),
            filling,
            bunName,
            bunPrice*2+
            ingredient1Price+
            ingredient2Price
        );

        System.out.println(expectedReceipt);

        MatcherAssert.assertThat(burger.getReceipt(), is(expectedReceipt));
        verify(burger, Mockito.times(1)).getPrice();
        verify(mockIngredient1, Mockito.times(1)).getType();
        verify(mockIngredient2, Mockito.times(1)).getType();
    }


}
