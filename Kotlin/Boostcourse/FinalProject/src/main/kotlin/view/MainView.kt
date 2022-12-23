package view

import app.Styles
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }
        button("Press Button"){
            action{ //온클릭
                information("Thank u") //팝업
            }
        }
    }
}