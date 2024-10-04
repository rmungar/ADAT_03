package org.example.part2

import org.example.console
import org.example.part1.Employees
import org.example.part1.fileReader
import org.w3c.dom.DOMImplementation
import org.w3c.dom.Document
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class xmlWriter() {

    private val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()

    private val builder: DocumentBuilder = factory.newDocumentBuilder()

    private val imp: DOMImplementation = builder.domImplementation

    private val fileReader = fileReader()

    private val filePath = "${System.getProperty("user.dir")}\\src\\main\\resources\\employees.xml"

    fun writeFile(employeesList: List<Employees>){


        try {
            val newEmployeesFile = File(filePath)

            if (!newEmployeesFile.exists()) {
                newEmployeesFile.createNewFile()
            }
            val document: Document = imp.createDocument(null,"employees", null)

            for (employee in employeesList){
                val employeeXML = document.createElement("employee")
                employeeXML.setAttribute("id", "${employee.id}")

                document.documentElement.appendChild(employeeXML)

                val employeeSurnameXML = document.createElement("surname")
                val employeeDepartmentXML = document.createElement("department")
                val employeeSalaryXML = document.createElement("salary")

                val employeeSurname = document.createTextNode(employee.surname)
                val employeeDepartment = document.createTextNode(employee.department)
                val employeeSalary = document.createTextNode(employee.salary.toString())

                employeeSurnameXML.appendChild(employeeSurname)
                employeeDepartmentXML.appendChild(employeeDepartment)
                employeeSalaryXML.appendChild(employeeSalary)

                employeeXML.appendChild(employeeSurnameXML)
                employeeXML.appendChild(employeeDepartmentXML)
                employeeXML.appendChild(employeeSalaryXML)

            }
            val source = DOMSource(document)
            val result: StreamResult = StreamResult(newEmployeesFile)

            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")

            transformer.transform(source, result)
        }
        catch (e:Exception){
            println(e)
        }
    }


    fun editEmployee(id: Int, newSalary: Number){
        try {
            val currentFileContent = fileReader.readEmployeesCsvFile()
            val newFileContent = mutableListOf<Employees>()
            currentFileContent.forEach {
                if (it.id == id) {
                    it.salary = newSalary.toString().format("%.2f").toDouble()
                }
                newFileContent.add(it)
            }

            writeFile(newFileContent)

        }catch (e:Exception){
            console.printText(e.message!!)
        }
    }
}