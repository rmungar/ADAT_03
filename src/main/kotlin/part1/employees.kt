package org.example.part1

data class Employees(val id: Int, val surname: String, val department: String, var salary: Double) {

    override fun toString(): String {
        return "ID: $id, Apellido: $surname, Departamento: $department, Salario: $salary"
    }
}