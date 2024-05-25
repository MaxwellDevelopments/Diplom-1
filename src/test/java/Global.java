import com.github.javafaker.Faker;

import java.util.Random;

public class Global {
    private Global() {

    }

    public static String getFakeName() {
        return new Faker().name().firstName();
    }

    public static String getFakeIngredient() {
        return new Faker().food().ingredient();
    }

    public static String getFakeSauce() {
        return new Faker().food().spice();
    }

    public static float getFakePrice() {
        return new Random().nextFloat();
    }
}
