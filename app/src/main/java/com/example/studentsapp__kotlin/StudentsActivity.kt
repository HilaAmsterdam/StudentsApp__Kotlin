package com.example.studentsapp__kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp__kotlin.models.Model

class StudentsActivity : AppCompatActivity() {
    private lateinit var studentsAdapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)

        // First, load the saved students from SharedPreferences
        Model.shared.loadStudents(this)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val addStudentButton: View = findViewById(R.id.addStudentButton)

        // Set up the RecyclerView
        studentsAdapter = StudentsAdapter(
            Model.shared.students,
            onStudentClicked = { position ->
                // If you have an EditStudentActivity, you can start it here:
                // val intent = Intent(this, EditStudentActivity::class.java)
                // intent.putExtra("student_position", position)
                // startActivity(intent)
            },
            onCheckBoxClicked = { position, isChecked ->
                Model.shared.students[position].isChecked = isChecked
                // Optionally save immediately whenever a checkbox changes:
                // Model.shared.saveStudents(this)
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentsAdapter

        // Handle Add Student button click
        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddNewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Update the list in case anything has changed
        studentsAdapter.notifyDataSetChanged()
    }
}
