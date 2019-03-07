package com.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.recipe.domain.Category;
import com.recipe.domain.Difficulty;
import com.recipe.domain.Ingredient;
import com.recipe.domain.Notes;
import com.recipe.domain.Recipe;
import com.recipe.domain.UnitOfMeasure;
import com.recipe.repository.CategoryRepository;
import com.recipe.repository.RecipeRepository;
import com.recipe.repository.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	private CategoryRepository categoryRepository;

	public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			CategoryRepository categoryRepository) {
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		log.debug("saving recipes...");
		recipeRepository.saveAll(getRecipes());
	}

	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>();

		Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("teaspoon");
		if (!teaspoonOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> tbspOptional = unitOfMeasureRepository.findByDescription("Tbsp");
		if (!tbspOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByDescription("Tbsp");
		if (!eachOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		UnitOfMeasure teaspoonUom = teaspoonOptional.get();
		UnitOfMeasure tbspUom = tbspOptional.get();
		UnitOfMeasure eachUom = eachOptional.get();

		Optional<Category> americanOptional = categoryRepository.findByDescription("American");
		if (!americanOptional.isPresent()) {
			throw new RuntimeException("Expected category not found!");
		}
		Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
		if (!mexicanOptional.isPresent()) {
			throw new RuntimeException("Expected category not found!");
		}
		Category americanCategory = americanOptional.get();
		Category mexicanCategory = mexicanOptional.get();

		// Perfect Guacamole
		Recipe gaucRecipe = new Recipe();
		gaucRecipe.setDescription("Perfect Guacamole");
		gaucRecipe.setPrepTime(10);
		gaucRecipe.setCookTime(0);
		gaucRecipe.setServings(4);
		gaucRecipe.setDifficulty(Difficulty.EASY);
		gaucRecipe.setDirections(
				"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
						+ "\n"
						+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
						+ "\n"
						+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown."
						+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness."
						+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste."
						+ "\n"
						+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
						+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

		Notes gaucNotes = new Notes();
		gaucNotes.setRecipeNotes(
				"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados."
						+ "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole)."
						+ "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole."
						+ "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great."
						+ "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
		gaucRecipe.setNotes(gaucNotes);

		gaucRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
		gaucRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(0.5), teaspoonUom));
		gaucRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tbspUom));
		gaucRecipe.addIngredient(
				new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tbspUom));
		gaucRecipe.addIngredient(
				new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
		gaucRecipe.addIngredient(
				new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), teaspoonUom));

		gaucRecipe.getCategories().add(americanCategory);
		gaucRecipe.getCategories().add(mexicanCategory);

		recipes.add(gaucRecipe);

		// Spicy Grilled Chicken Tacos
		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setCookTime(15);
		tacosRecipe.setServings(6);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);
		tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat." + "\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over."
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings." + "\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes."
				+ "\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side."
				+ "Wrap warmed tortillas in a tea towel to keep them warm until serving." + "\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

		Notes tacosNotes = new Notes();
		tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla." + "\n"
				+ "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house."
				+ "\n"
				+ "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!");
		tacosRecipe.setNotes(tacosNotes);

		tacosRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tbspUom));
		tacosRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom));
		tacosRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom));
		tacosRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUom));
		tacosRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaspoonUom));
		tacosRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUom));

		tacosRecipe.getCategories().add(americanCategory);
		tacosRecipe.getCategories().add(mexicanCategory);

		recipes.add(tacosRecipe);

		return recipes;
	}
}
