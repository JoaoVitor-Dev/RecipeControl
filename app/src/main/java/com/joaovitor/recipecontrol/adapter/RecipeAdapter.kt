package com.joaovitor.recipecontrol.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.joaovitor.recipecontrol.data.entity.Recipe

class RecipeAdapter: BaseAdapter {

    var recipes: List<Recipe> = ArrayList<Recipe>()

    constructor(recipes: List<Recipe>) : super() {
        this.recipes = recipes
    }

    override fun getCount(): Int {
        return recipes.count()
    }

    override fun getItem(position: Int): Any? {
        return recipes.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        TODO("Not yet implemented")
    }
}