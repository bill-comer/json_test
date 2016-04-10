package uk.co.beanfactory.sainsbury;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by bill on 10/04/2016.
 */
public class ShoppingDisplayDeserializer implements JsonSerializer<ShoppingDisplay> {
    @Override
    public JsonElement serialize(ShoppingDisplay shoppingDisplay, Type type,
                                 JsonSerializationContext jsonSerializationContext) {

        JsonObject result = new JsonObject();

        //result.add("results", );

        shoppingDisplay.results.forEach(item -> {
            //result.add("results",  ));
        });

        return result;
    }
}
