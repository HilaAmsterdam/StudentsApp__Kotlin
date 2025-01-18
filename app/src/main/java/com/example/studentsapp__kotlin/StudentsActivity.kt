package com.example.studentsapp__kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp__kotlin.models.Model
import com.example.studentsapp__kotlin.models.Student

class StudentsActivity : AppCompatActivity() {
    private lateinit var studentsAdapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)

        Model.shared.loadStudents(this)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val addStudentButton: View = findViewById(R.id.addStudentButton)

        studentsAdapter = StudentsAdapter(
            Model.shared.students,
            onStudentClicked = { position ->
                val student: Student = Model.shared.students[position]
                val intent = Intent(this, StudentDetails::class.java)
                intent.putExtra("student", student)
                intent.putExtra("position", position)
                startActivity(intent)
            },
            onCheckBoxClicked = { position, isChecked ->
                Model.shared.students[position].isChecked = isChecked

            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentsAdapter

        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddNewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        studentsAdapter.notifyDataSetChanged()
    }
}
