/**
 * 
 */
package example;

import core.XMLBurger;

/**
 * @author Samuel Croset
 *
 */


public class CheeseBurgerExample {
    public static void main(String[] args) {
	XMLBurger cheeseburger = new XMLBurger("data/cheeseburger_demo.xml");
	Sandwich sandwich = new Sandwich();
	while(cheeseburger.isNotOver()){
	    if(cheeseburger.tag("Bread")){
		Bread bread = new Bread();
		String id = cheeseburger.getTagAttribute("id");
		bread.setId(id);
		sandwich.getIngredients().add(bread);
	    }
	    if(cheeseburger.tag("Filling")){
		while(cheeseburger.inTag("Filling")){
		    if(cheeseburger.tag("Tomato")){
			Tomato tomato = new Tomato();
			String color = cheeseburger.getTagAttribute("color");
			String format = cheeseburger.getTagText();
			tomato.setColor(color);
			tomato.setFormat(format);
			sandwich.getIngredients().add(tomato);
		    }
		}
	    }
	}	
    }
}


