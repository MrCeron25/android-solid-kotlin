package com.example.pr11.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pr11.R
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.parsers.SexParserImpl
import com.example.pr11.view.EditFragment
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

class StudentIndexesAdapter(
    private val baseViewModel: BaseViewModel,
    private val navController: NavController,
    val list: StateFlow<List<Pair<Int, Int>>>
) : RecyclerView.Adapter<StudentIndexesAdapter.ViewHolder>() {

    /** Provide a reference to the type of views that you are using (custom ViewHolder). **/
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val surname: TextView
        val name: TextView
        val patronymic: TextView
        val age: TextView
        val sex: TextView
        val deleteButton: Button
        val changeButton: Button
        val imageView: ImageView

        init {
            surname = view.findViewById(R.id.editTextStudentSurname)
            name = view.findViewById(R.id.editTextStudentName)
            patronymic = view.findViewById(R.id.editTextStudentPatronymic)
            age = view.findViewById(R.id.editTextStudentAge)
            sex = view.findViewById(R.id.spinnerStudentSex)
            deleteButton = view.findViewById(R.id.deleteStudent)
            changeButton = view.findViewById(R.id.changeStudent)
            imageView = view.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.student_template, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val sexParserImpl = SexParserImpl()

        val findDataBaseIndex = _list[position].first
        val studentDataBaseIndex = _list[position].second

        val studentDataBase = baseViewModel.dataBaseViewModel.dataBaseFlow.value
        val student = studentDataBase[studentDataBaseIndex]

        viewHolder.surname.text = student.surname
        viewHolder.name.text = student.name
        viewHolder.patronymic.text = student.patronymic
        viewHolder.age.text = student.age.toString()
        viewHolder.sex.text = when (student.sex) {
            Sex.MAN -> sexParserImpl.man
            Sex.WOMAN -> sexParserImpl.woman
            Sex.UNDEFINED -> sexParserImpl.undefined
        }
        if (student.image == null) {
            viewHolder.imageView.setImageResource(R.drawable.clown)
        } else {
            viewHolder.imageView.setImageBitmap(student.image)
        }

        // viewHolder click on listener for buttons
        viewHolder.deleteButton.setOnClickListener {
            baseViewModel.dataBaseViewModel.delete(studentDataBaseIndex)
            baseViewModel.dataBaseViewModel.deleteIndex(findDataBaseIndex)
        }
        viewHolder.changeButton.setOnClickListener {
            navController.navigate(
                R.id.action_viewFragment_to_editFragment,
                bundleOf(EditFragment.POSITION to studentDataBaseIndex) // position Index in list
            )
        }
    }

    private var _list = list.value

    override fun getItemCount(): Int {
        _list = list.value
        return _list.size
    }
}