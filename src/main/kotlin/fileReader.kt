package org.example

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class fileReader() {


    fun readEmployeesFile(employeesFile: File) {

        val fileContent = employeesFile.useLines { it.toList() }
        val employeesList = mutableListOf<Employees>()

        for (line in fileContent) {
            val currentLine = line.split(",")
            if (line != fileContent[0]){
                val employee = Employees(
                    currentLine[0].toInt(),
                    currentLine[1]
                )


            }

        }


    }

}