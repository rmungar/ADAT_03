package org.example

import org.example.part1.fileReader
import org.example.part2.xmlWriter
import java.io.File

class menu {


    fun printMenu() {
        console.printText()
        console.printText("---------MENU----------")
        console.printText("1 - PRINT ALL EMPLOYEES (CSV)")
        console.printText("2 - WRITE XML FILE")
        console.printText("3 - MODIFY EMPLOYEE (XML)")
        console.printText("Input anything else to exit.")
        console.printText()
        console.printText("> ", false)

    }


    fun startMenu() {

        while (true) {
            printMenu()
            when (console.getInput()) {
                "1" -> {
                    // PRIMERA PARTE, LECTURA DEL ARCHIVO CSV

                    val employeesFile = File("${System.getProperty("user.dir")}\\src\\main\\resources\\empleados.csv")

                    val fileReader = fileReader()

                    console.printText("-----EMPLOYEES LIST-----")

                    fileReader.readEmployeesCsvFile(employeesFile).forEach { console.printText(it.toString()) }
                }

                "2" -> {
                    // SEGUNDA PARTE, ESCRITURA DE UN ARCHIVO XML

                    val fileReader = fileReader()

                    val employeesList = fileReader.readEmployeesCsvFile()

                    val xmlWriter = xmlWriter()

                    xmlWriter.writeFile(employeesList)

                    console.printText("File written.\n")



                }

                "3" -> {

                    // TERCERA PARTE, MODIFICACIÓN DE UN ARCHIVO XML

                    val fileReader = fileReader()

                    while (true) {
                        var validId = false
                        var validSalary = false
                        console.printText("Please enter a valid id and salary.")
                        try {

                            val xmlWriter = xmlWriter()

                            console.printText("ID > ", false)
                            val id = console.getInput().toInt()
                            console.printText()
                            validId = true

                            console.printText("Salary (XXXX.XX) > ", false)
                            val salary = console.getInput().toDouble()
                            console.printText()
                            validSalary = true

                            xmlWriter.editEmployee(id, salary)
                            console.printText("Employee modified successfully.")
                            console.printText()
                            break

                        } catch (e: NumberFormatException) {

                            if (validId && !validSalary) console.printText("The value supplied for the employee's salary was not accepted.")
                            else console.printText("The value supplied for the employee's id was not accepted.")
                        }
                    }
                    console.printText("-----EMPLOYEES LIST-----")

                    fileReader.readEmployeesXmlFile().forEach { console.printText(it.toString()) }

                    console.printText()
                }

                else -> {
                    break
                }
            }
        }
    }
}

