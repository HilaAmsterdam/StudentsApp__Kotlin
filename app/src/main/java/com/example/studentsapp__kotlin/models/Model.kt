package com.example.studentsapp__kotlin.models

class Model private constructor(){
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..20) {
            val student = Student(
                name = "Name $i",
                id = "Student ID:$i",
                avatarUrl = "",
                isChecked = false,
                phone = "555-000$i",
                address = "Address $i"
            )
            students.add(student)
        }
    }
}