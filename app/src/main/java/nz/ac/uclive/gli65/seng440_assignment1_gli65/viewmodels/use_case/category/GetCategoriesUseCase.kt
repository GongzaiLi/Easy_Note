package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category

import androidx.lifecycle.LiveData
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.ICategoryRepository

class GetCategoriesUseCase(private val repository: ICategoryRepository) {

    operator fun invoke(): LiveData<List<Category>> { // todo can be
        return repository.getCategories()
    }
}