package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case

import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.ICategoryRepository

class PickCategoryUseCase(private val repository: ICategoryRepository) {

//     operator fun invoke(category: Category): Flow<Category>? {
//        return repository.getCategoryById(category.id) // todo check null
//    }
}