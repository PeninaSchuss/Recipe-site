document.addEventListener("DOMContentLoaded", function() {
    const addIngredientBtn = document.getElementById("addIngredientBtn");
    const ingredientsContainer = document.getElementById("ingredientsContainer");
    let ingredientCount = document.getElementsByClassName("ingredient-row").length;

    addIngredientBtn.addEventListener("click", function() {
        const ingredientRow = document.createElement("div");
        ingredientRow.className = "form-row ingredient-row";

        const ingredientNameDiv = document.createElement("div");
        ingredientNameDiv.className = "form-group col-md-6";
        const ingredientNameInput = document.createElement("input");
        ingredientNameInput.type = "text";
        ingredientNameInput.className = "form-control ingredient-name";
        ingredientNameInput.placeholder = "Ingredient Name";
        ingredientNameInput.name = "ingredients[" + ingredientCount + "].ingredientName";
        ingredientNameInput.required = true; // Add required attribute
        ingredientNameInput.pattern = "^(?!\\s*$).+"; // Pattern to disallow just spaces
        ingredientNameInput.title = "Ingredient name can not be only spaces";
        ingredientNameDiv.appendChild(ingredientNameInput);

        const ingredientQuantityDiv = document.createElement("div");
        ingredientQuantityDiv.className = "form-group col-md-4";
        const ingredientQuantityInput = document.createElement("input");
        ingredientQuantityInput.type = "text";
        ingredientQuantityInput.className = "form-control ingredient-quantity";
        ingredientQuantityInput.placeholder = "Quantity";
        ingredientQuantityInput.name = "ingredients[" + ingredientCount + "].quantity";
        ingredientQuantityInput.required = true; // Add required attribute
        ingredientQuantityInput.pattern = "^(?!\\s*$).+"; // Pattern to disallow just spaces
        ingredientQuantityInput.title = "Quantity can not be only spaces";
        ingredientQuantityDiv.appendChild(ingredientQuantityInput);

        const removeIngredientBtnDiv = document.createElement("div");
        removeIngredientBtnDiv.className = "form-group col-md-2";
        const removeIngredientBtn = document.createElement("button");
        removeIngredientBtn.type = "button";
        removeIngredientBtn.className = "btn btn-danger remove-ingredient-btn";
        removeIngredientBtn.innerHTML = "Remove";
        removeIngredientBtn.addEventListener("click", function() {
            ingredientsContainer.removeChild(ingredientRow);
        });
        removeIngredientBtnDiv.appendChild(removeIngredientBtn);

        ingredientRow.appendChild(ingredientNameDiv);
        ingredientRow.appendChild(ingredientQuantityDiv);
        ingredientRow.appendChild(removeIngredientBtnDiv);

        ingredientsContainer.appendChild(ingredientRow);

        ingredientCount++;
    });
});
