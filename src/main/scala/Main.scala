import scala.swing._
import scala.swing.event._
import java.awt.Color
import java.util.{Timer, TimerTask}

import model._
import view._

object Main extends SimpleSwingApplication {
	var model = Model.empty
	def view = new View
	def top = new MainFrame {
		peer.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE)
		title = "tetris"
		contents = component
		size = new Dimension(32*8+32, 32*20+32)
		location = new Point(0, 0)
		override def closeOperation() = {
			component.timer.cancel()
			System.exit(0)
		}
	}
	val component = new Component {
		override def paintComponent(g: Graphics2D) = {
			super.paintComponent(g)
			view.render(g, model)
		}
		def frame() = {
			model = model.down
			repaint
		}
		listenTo(keys)
		val reaction: PartialFunction[Event, Model] = {
			case KeyPressed(_, Key.D, _, _) => model.right
			case KeyPressed(_, Key.A, _, _) => model.left
			case KeyPressed(_, Key.W, _, _) => model.rotate
			case KeyPressed(_, Key.S, _, _) => model.drop
		}
		reactions += new PartialFunction[Event, Unit] {
			def apply(e: Event) = {
				model = reaction(e)
				repaint
			}
			def isDefinedAt(e: Event) = reaction.isDefinedAt(e)
		}
		val timer = new Timer("drops")
		timer.schedule(
			new TimerTask {
				def run() = frame()
			},
			800,
			800,
		)
		focusable = true
		requestFocus
	}
}
