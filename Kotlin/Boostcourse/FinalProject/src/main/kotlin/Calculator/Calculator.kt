package Calculator

import javafx.fxml.FXML
import Calculator.Operator.*
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.KeyEvent
import javafx.scene.layout.VBox
import tornadofx.*

class Calculator : View(){
    //override val root: VBox by fxml()

    override val root: Parent = FXMLLoader.load(javaClass.getResource("/Calculator.fxml"))

    @FXML
    lateinit var display: Label
    

    init {
        title = "kt Calculator"

        root.lookupAll(".button").forEach { b ->
            b.setOnMouseClicked {
                operator((b as Button).text)
            }
        }

        root.addEventFilter(KeyEvent.KEY_TYPED) {
            operator(it.character.toUpperCase().replace("\r", "="))
        }

    }

    var state: Operator = add(0)

    private fun onAction(fn: Operator) {
        state = fn
        display.text = ""
    }

    val displayValue: Long
        get() = when(display.text) {
            "" -> 0
            else -> display.text.toLong()
        }

    private fun operator(x: String) {
        if (Regex("[0-9]").matches(x)) {
            display.text += x
        } else {
            when(x) {
                "+" -> onAction(add(displayValue))
                "-" -> onAction(sub(displayValue))
                "/" -> onAction(div(displayValue))
                "%" -> { onAction(add(displayValue /100)); operator("=") }
                "X" -> onAction(mul(displayValue))
                "C" -> onAction(add(0))
                "+/-" -> { onAction(add(-1* displayValue)); operator("=") }
                "=" -> display.text = state.calculate(displayValue).toString()
            }
        }
    }

}



