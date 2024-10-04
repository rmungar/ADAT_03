package org.example

object console {


    fun printText(text: String = "", lineBreak: Boolean = true) {

        if (lineBreak) println(text)
        else print(text)

    }

    fun getInput(): String {

        while (true){
            val input = readln()
            return input
        }
    }


}