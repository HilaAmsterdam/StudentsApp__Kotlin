package com.example.studentsapp__kotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp__kotlin.models.Model
import com.example.studentsapp__kotlin.models.Student


class StudentDetails : AppCompatActivity() {
    private var studentPosition: Int = -1
    private lateinit var student: Student
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentPosition = intent.getIntExtra("position", -1)
        if (studentPosition < 0 || studentPosition >= Model.shared.students.size) {
            finish()
            return
        }
        student = Model.shared.students[studentPosition]
        val nameTextView: TextView = findViewById(R.id.student_details_activity_name_text_view)
        val idTextView: TextView = findViewById(R.id.student_details_activity_id_text_view)
        val phoneTextView: TextView = findViewById(R.id.student_details_activity_phone_text_view)
        val addressTextView: TextView = findViewById(R.id.student_details_activity_address_text_view)
        val checkBox: CheckBox = findViewById(R.id.student_details_activity_checkoBox)
        val editButton: Button = findViewById(R.id.student_details_activity_edit_button)

            nameTextView.text = "Name: ${student.name}"
            idTextView.text = "ID: ${student.id}"
            phoneTextView.text = "Phone: ${student.phone}"
            addressTextView.text = "Address: ${student.address}"
            checkBox.isChecked = student.isChecked

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudent::class.java)
            intent.putExtra("position", studentPosition)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        if (studentPosition >= 0 && studentPosition < Model.shared.students.size) {
            student = Model.shared.students[studentPosition]
            val nameTextView: TextView = findViewById(R.id.student_details_activity_name_text_view)
            val idTextView: TextView = findViewById(R.id.student_details_activity_id_text_view)
            val phoneTextView: TextView = findViewById(R.id.student_details_activity_phone_text_view)
            val addressTextView: TextView = findViewById(R.id.student_details_activity_address_text_view)
            val checkBox: CheckBox = findViewById(R.id.student_details_activity_checkoBox)

            nameTextView.text = "Name: ${student.name}"
            idTextView.text = "ID: ${student.id}"
            phoneTextView.text = "Phone: ${student.phone}"
            addressTextView.text = "Address: ${student.address}"
            checkBox.isChecked = student.isChecked
        }
    }
}


