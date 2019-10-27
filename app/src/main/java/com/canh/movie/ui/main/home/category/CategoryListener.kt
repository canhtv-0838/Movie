package com.canh.movie.ui.main.home.category

interface CategoryListener<T> : CategoryAdapter.OnCategoryClickListener {
    fun onCategoryClick(item: T)
}
