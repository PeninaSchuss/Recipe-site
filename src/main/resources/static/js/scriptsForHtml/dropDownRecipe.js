
$(document).ready(function() {
    const recipeTypeSelect = $('#recipeTypeSelect');
    const dishTypeField = $('#dishTypeField');

    recipeTypeSelect.on('change', function() {
        if (recipeTypeSelect.val().trim() !== '') {
            dishTypeField.show();
        } else {
            dishTypeField.hide(); // Hide the dishType field
        }

        let optionsHTML = '<label for="dishType" class="col-form-label">Dish Type</label>';
        optionsHTML += '<select class="form-control" id="dishType" name="dishType" th:field="*{dishType}">';
        optionsHTML += '<option value=""></option>';

        //use ajax to get the dish types for the selected recipe type from the server
        $.ajax({
            url: '/admin/get-dish-types',
            data: {
                categoryName: recipeTypeSelect.val()
            },
            success: function(data) {
                data.forEach(function(dishType) {
                    optionsHTML += '<option value="' + dishType + '">' + dishType + '</option>';
                });
            },
            async: false
        });

        optionsHTML += '</select>';
        optionsHTML += '<span th:if="${#fields.hasErrors(\'dishType\')}" th:errors="*{dishType}" class="text-danger"></span>';

        dishTypeField.html(optionsHTML);
    });
});
