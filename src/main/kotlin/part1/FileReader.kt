package org.example.part1

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory


class fileReader() {

    private val dbf = DocumentBuilderFactory.newInstance()

    private val db = dbf.newDocumentBuilder()

    private val defaultCsvFile = File("${System.getProperty("user.dir")}\\src\\main\\resources\\empleados.csv")
    private val defaultXmlFile = File("${System.getProperty("user.dir")}\\src\\main\\resources\\employees.xml")

    fun readEmployeesCsvFile(employeesFile: File = defaultCsvFile): List<Employees> {

        val fileContent = employeesFile.useLines { it.toList() }
        val employeesList = mutableListOf<Employees>()

        for (line in fileContent) {
            val currentLine = line.split(",")
            if (line != fileContent[0]){
                val employee = Employees(
                    currentLine[0].toInt(),
                    currentLine[1],
                    currentLine[2],
                    currentLine[3].toDouble()
                )
                employeesList.add(employee)

            }
        }
        return employeesList
    }

    fun readEmployeesXmlFile(xmlFile: File = defaultXmlFile): List<Employees> {
        val document = db.parse(xmlFile)

        val documentRoot = document.documentElement

        documentRoot.normalize()

        val nodeList = documentRoot.getElementsByTagName("employee")

        val employeeList = mutableListOf<Employees>()

        for (i in 0..<nodeList.length){

            val node = nodeList.item(i)

            if (node.nodeType == Node.ELEMENT_NODE){

                val elementNode = node as Element
                val elementId = elementNode.attributes.getNamedItem("id")
                val elementSurname = elementNode.getElementsByTagName("surname")
                val elementDepartment = elementNode.getElementsByTagName("department")
                val elementSalary = elementNode.getElementsByTagName("salary")


                val employee = Employees(
                    elementId.textContent.toInt(),
                    elementSurname.item(0).textContent,
                    elementDepartment.item(0).textContent,
                    elementSalary.item(0).textContent.toDouble()
                )

                employeeList.add(employee)
            }

        }
        return employeeList
    }

}