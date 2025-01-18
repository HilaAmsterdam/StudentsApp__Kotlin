package com.example.studentsapp__kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp__kotlin.models.Model
import com.example.studentsapp__kotlin.models.Student

class StudentsAdapter(
    private val students: List<Student>,
    private val onStudentClicked: (Int) -> Unit,
    private val onCheckBoxClicked: (Int, Boolean) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val studentImageView: ImageView = view.findViewById(R.id.studentImageView)
        val studentNameTextView: TextView = view.findViewById(R.id.studentNameTextView)
        val studentCheckbox: CheckBox = view.findViewById(R.id.studentCheckbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_row, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        // Set the student name and checkbox state
        holder.studentNameTextView.text = "${student.name} (${student.id})"
        holder.studentCheckbox.setOnCheckedChangeListener(null) // Reset listener to prevent unwanted triggers
        holder.studentCheckbox.isChecked = student.isChecked

        // Handle checkbox click
        holder.studentCheckbox.setOnCheckedChangeListener { _, isChecked ->
            onCheckBoxClicked(position, isChecked)
        }

        // Handle row click
        holder.itemView.setOnClickListener {
            onStudentClicked(position)
        }
    }

    override fun getItemCount(): Int = students.size
}
