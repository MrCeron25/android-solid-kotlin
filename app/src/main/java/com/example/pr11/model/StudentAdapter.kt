package com.example.pr11.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pr11.R
import com.example.pr11.kotlin.student.StudentImpl
import com.example.pr11.view_model.StudentViewModel
import kotlinx.coroutines.flow.StateFlow

class StudentAdapter(val list: StateFlow<List<StudentImpl>>) : // viewModel: StudentViewModel
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    /** Provide a reference to the type of views that you are using (custom ViewHolder). **/
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val surname: TextView
        val name: TextView
        val patronymic: TextView
        val age: TextView
        val sex: TextView

        init {
            surname = view.findViewById(R.id.editTextStudentSurname)
            name = view.findViewById(R.id.editTextStudentName)
            patronymic = view.findViewById(R.id.editTextStudentPatronymic)
            age = view.findViewById(R.id.editTextStudentAge)
            sex = view.findViewById(R.id.spinnerStudentSex)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.student_template, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.surname.text = _list[position].surname
        viewHolder.name.text = _list[position].name
        viewHolder.patronymic.text = _list[position].patronymic
        viewHolder.age.text = _list[position].age.toString()
        viewHolder.sex.text = _list[position].sex.toString()
        // viewHolder click on listener for buttons
    }

   private var _list = list.value

    override fun getItemCount():Int {
        _list = list.value
        return _list.size
    }
}