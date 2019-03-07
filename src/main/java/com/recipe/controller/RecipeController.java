package com.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.command.RecipeCommand;
import com.recipe.service.RecipeServiceImpl;

@Controller
public class RecipeController {

	private RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(path = "recipe/show/{id}")
    public String getRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
        return "recipe/show";
    }

    @RequestMapping(path = "recipe/new")
    public String newRecipeForm(Model model) {
        RecipeCommand recipeCommand = new RecipeCommand();
        model.addAttribute("recipe", recipeCommand);
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }

    @RequestMapping(path = "recipe/update/{id}")
    public String updateRecipeForm(@PathVariable String id,  Model model) {
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(new Long(id));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/recipeform";
    }
    
    @RequestMapping(path = "recipe/delete/{id}")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipeById(new Long(id));
        return "redirect:/";
    }
    
}