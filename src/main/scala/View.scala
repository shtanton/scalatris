package view

import scala.swing._
import java.awt.Color

import model._

class View {
	def backgroundColor = new Color(0, 0, 0)
	def tileColor = new Color(0.5f, 0.5f, 1)
	def blockColor = new Color(1, 0.5f, 1)
	def render(g: Graphics2D, model: Model) = {
		g.setColor(backgroundColor)
		g.fillRect(0, 0, 32*8, 32*20)
		renderTiles(g, tileColor, model.grid.tiles)
		renderTiles(g, blockColor, model.block.tiles)
	}
	def renderTiles(g: Graphics2D, color: Color, tiles: Iterator[(Int, Int)]) = {
		g.setColor(color)
		for ((x, y) <- tiles) {
			g.fillRect(x*32+1, y*32+1, 30, 30)
		}
	}
}
