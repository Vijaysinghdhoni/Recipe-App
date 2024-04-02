package com.example.recipeapp.data.remote


import com.example.recipeapp.domain.model.Meal
import com.google.gson.annotations.SerializedName

data class MealDto(
    @SerializedName("dateModified")
    val dateModified: Any?,
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strArea")
    val strArea: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: Any?,
    @SerializedName("strDrinkAlternate")
    val strDrinkAlternate: Any?,
    @SerializedName("strImageSource")
    val strImageSource: Any?,
    @SerializedName("strIngredient1")
    val strIngredient1: String?,
    @SerializedName("strIngredient10")
    val strIngredient10: String?,
    @SerializedName("strIngredient11")
    val strIngredient11: String?,
    @SerializedName("strIngredient12")
    val strIngredient12: String?,
    @SerializedName("strIngredient13")
    val strIngredient13: String?,
    @SerializedName("strIngredient14")
    val strIngredient14: String?,
    @SerializedName("strIngredient15")
    val strIngredient15: String?,
    @SerializedName("strIngredient16")
    val strIngredient16: Any?,
    @SerializedName("strIngredient17")
    val strIngredient17: Any?,
    @SerializedName("strIngredient18")
    val strIngredient18: Any?,
    @SerializedName("strIngredient19")
    val strIngredient19: Any?,
    @SerializedName("strIngredient2")
    val strIngredient2: String?,
    @SerializedName("strIngredient20")
    val strIngredient20: Any?,
    @SerializedName("strIngredient3")
    val strIngredient3: String?,
    @SerializedName("strIngredient4")
    val strIngredient4: String?,
    @SerializedName("strIngredient5")
    val strIngredient5: String?,
    @SerializedName("strIngredient6")
    val strIngredient6: String?,
    @SerializedName("strIngredient7")
    val strIngredient7: String?,
    @SerializedName("strIngredient8")
    val strIngredient8: String?,
    @SerializedName("strIngredient9")
    val strIngredient9: String?,
    @SerializedName("strInstructions")
    val strInstructions: String?,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String?,
    @SerializedName("strMeasure1")
    val strMeasure1: String?,
    @SerializedName("strMeasure10")
    val strMeasure10: String?,
    @SerializedName("strMeasure11")
    val strMeasure11: String?,
    @SerializedName("strMeasure12")
    val strMeasure12: String?,
    @SerializedName("strMeasure13")
    val strMeasure13: String?,
    @SerializedName("strMeasure14")
    val strMeasure14: String?,
    @SerializedName("strMeasure15")
    val strMeasure15: String?,
    @SerializedName("strMeasure16")
    val strMeasure16: Any?,
    @SerializedName("strMeasure17")
    val strMeasure17: Any?,
    @SerializedName("strMeasure18")
    val strMeasure18: Any?,
    @SerializedName("strMeasure19")
    val strMeasure19: Any?,
    @SerializedName("strMeasure2")
    val strMeasure2: String?,
    @SerializedName("strMeasure20")
    val strMeasure20: Any?,
    @SerializedName("strMeasure3")
    val strMeasure3: String?,
    @SerializedName("strMeasure4")
    val strMeasure4: String?,
    @SerializedName("strMeasure5")
    val strMeasure5: String?,
    @SerializedName("strMeasure6")
    val strMeasure6: String?,
    @SerializedName("strMeasure7")
    val strMeasure7: String?,
    @SerializedName("strMeasure8")
    val strMeasure8: String?,
    @SerializedName("strMeasure9")
    val strMeasure9: String?,
    @SerializedName("strSource")
    val strSource: Any?,
    @SerializedName("strTags")
    val strTags: String?,
    @SerializedName("strYoutube")
    val strYoutube: String?
)


fun MealDto.toMeal(): Meal {

    return Meal(
        mealId = idMeal,
        mealArea = strArea,
        mealCategory = strCategory,
        ingredient1 = strIngredient1,
        ingredient2 = strIngredient2,
        ingredient3 = strIngredient3,
        ingredient4 = strIngredient4,
        ingredient5 = strIngredient5,
        ingredient6 = strIngredient6,
        ingredient7 = strIngredient7,
        ingredient8 = strIngredient8,
        ingredient9 = strIngredient9,
        ingredient10 = strIngredient10,
        ingredient11 = strIngredient11,
        ingredient12 = strIngredient12,
        ingredient13 = strIngredient13,
        ingredient14 = strIngredient14,
        ingredient15 = strIngredient15,
        ingredient16 = strIngredient16,
        ingredient17 = strIngredient17,
        ingredient18 = strIngredient18,
        ingredient19 = strIngredient19,
        ingredient20 = strIngredient20,
        strMeasure1 = strMeasure1,
        strMeasure2 = strMeasure2,
        strMeasure3 = strMeasure3,
        strMeasure4 = strMeasure4,
        strMeasure5 = strMeasure5,
        strMeasure6 = strMeasure6,
        strMeasure7 = strMeasure7,
        strMeasure8 = strMeasure8,
        strMeasure9 = strMeasure9,
        strMeasure10 = strMeasure10,
        strMeasure11 = strMeasure11,
        strMeasure12 = strMeasure12,
        strMeasure13 = strMeasure13,
        strMeasure14 = strMeasure14,
        strMeasure15 = strMeasure15,
        strMeasure16 = strMeasure16,
        strMeasure17 = strMeasure17,
        strMeasure18 = strMeasure18,
        strMeasure19 = strMeasure19,
        strMeasure20 = strMeasure20,
        mealSource = strSource,
        mealYoutubeLink = strYoutube,
        mealInstruction = strInstructions,
        mealName = strMeal,
        mealThumbImg = strMealThumb
    )

}