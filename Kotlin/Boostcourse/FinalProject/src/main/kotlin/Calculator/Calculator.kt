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
    // resources 하위 폴더가 app view 폴더 depth와 같아야함
    override val root: VBox by fxml()

//  Vbox로 받아야 Label write가능
//  override val root: Parent = FXMLLoader.load(javaClass.getResource("/Calculator/Calculator.fxml"))

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

    @FXML
    private fun onAction(fn: Operator) {
        state = fn
        display.text = ""
    }

    val displayValue: Long
        get() = when(display.text) {
            "" -> 0
            else -> display.text.toLong()
        }

    @FXML
    private fun operator(x: String) {
        if (Regex("[0-9]").matches(x)) {
            display.text += x
        } else {
            when(x) {
                "+" -> onAction(add(displayValue))
                "-" -> onAction(sub(displayValue))
                "/" -> onAction(div(displayValue))
                "%" -> { onAction(add(displayValue/100)); operator("=") } //백분율
                "X" -> onAction(mul(displayValue))
                "C" -> onAction(add(0))
                "+/-" -> { onAction(add(-1* displayValue)); operator("=") }
                "=" -> display.text = state.calculate(displayValue).toString()
            }
        }
    }

}



