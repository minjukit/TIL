package Calculator

import javafx.stage.Stage
import tornadofx.*

class CalApp : App() {
    override val primaryView = Calculator::class

    override fun start(stage: Stage) {
        importStylesheet("/Calculator/style.css")
        stage.isResizable = false
        super.start(stage)
    }
}