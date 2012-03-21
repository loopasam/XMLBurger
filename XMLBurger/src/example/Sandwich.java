/**
 * 
 */
package example;

import java.util.ArrayList;

/**
 * @author Samuel Croset
 *
 */
public class Sandwich {
    
    private ArrayList<Ingredient> ingredients;
    
    public Sandwich() {
	this.ingredients = new ArrayList<Ingredient>();
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
	this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
	return ingredients;
    }
    
}
