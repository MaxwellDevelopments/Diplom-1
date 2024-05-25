import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class IngredientTypeTests {
    String[] VALUES = {
            "SAUCE",
            "FILLING"
    };

    int INGREDIENT_TYPE_LENGTH = 2;

    @Test
    void testIngredientTypeSize() {
        assertThat(IngredientType.values().length, is(INGREDIENT_TYPE_LENGTH));
    }


    @Test
    void testIngredientValues() {
        var ingrNamesList = Stream.of(IngredientType.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());

        MatcherAssert.assertThat(ingrNamesList, hasItems(VALUES));
    }

}
