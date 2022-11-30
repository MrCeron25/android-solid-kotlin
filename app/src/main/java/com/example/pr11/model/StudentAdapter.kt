package com.example.pr11.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pr11.R
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.parsers.SexParserImpl
import com.example.pr11.kotlin.student.StudentImpl
import com.example.pr11.view.EditFragment
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

class StudentAdapter(
    val list: StateFlow<List<StudentImpl>>,
    private val baseViewModel: BaseViewModel,
    private val navController: NavController
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    /** Provide a reference to the type of views that you are using (custom ViewHolder). **/
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val surname: TextView
        val name: TextView
        val patronymic: TextView
        val age: TextView
        val sex: TextView
        val deleteButton: Button
        val changeButton: Button

        init {
            surname = view.findViewById(R.id.editTextStudentSurname)
            name = view.findViewById(R.id.editTextStudentName)
            patronymic = view.findViewById(R.id.editTextStudentPatronymic)
            age = view.findViewById(R.id.editTextStudentAge)
            sex = view.findViewById(R.id.spinnerStudentSex)
            deleteButton = view.findViewById(R.id.deleteStudent)
            changeButton = view.findViewById(R.id.changeStudent)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.student_template, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val sexParserImpl = SexParserImpl()
        viewHolder.surname.text = _list[position].surname
        viewHolder.name.text = _list[position].name
        viewHolder.patronymic.text = _list[position].patronymic
        viewHolder.age.text = _list[position].age.toString()
        viewHolder.sex.text = when (_list[position].sex) {
            Sex.MAN -> sexParserImpl.man
            Sex.WOMAN -> sexParserImpl.woman
            Sex.UNDEFINED -> sexParserImpl.undefined
        }
        // viewHolder click on listener for buttons
        viewHolder.deleteButton.setOnClickListener {
            baseViewModel.dataBaseViewModel.delete(position)
        }
        viewHolder.changeButton.setOnClickListener {
            navController.navigate(
                R.id.action_rootFragment_to_editFragment,
                bundleOf(EditFragment.POSITION to position) // position Index in list
            )
        }
    }

    private var _list = list.value

    override fun getItemCount(): Int {
        _list = list.value
        return _list.size
    }
}