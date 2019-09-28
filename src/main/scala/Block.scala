package block

import scala.swing._
import java.awt.Color
import scala.util.Random

object Block {
	val rnd = new Random
	val line = Array((-1, 0), (1, 0), (2, 0))
	val t = Array((-1, 0), (1, 0), (0, -1))
	val square = Array((1, 0), (0, -1), (1, -1))
	val z = Array((-1, -1), (0, -1), (1, 0))
	val s = Array((-1, 0), (0, -1), (1, -1))
	val l = Array((0, -1), (0, -2), (1, 0))
	val j = Array((0, -1), (0, -2), (-1, 0))
	val blocks = Array(line, t, square, s, z, l, j)
	def random = {
		blocks(rnd.nextInt(blocks.length))
	}
	implicit def tiles2block(tiles: Array[(Int, Int)]) = new Block(tiles, 4, 0)
}

class Block(_tiles: Array[(Int, Int)], x: Int, y: Int) {
	def down() = {
		new Block(_tiles, x, y + 1)
	}
	def left = {
		new Block(_tiles, x - 1, y)
	}
	def right = {
		new Block(_tiles, x + 1, y)
	}
	def tiles = {
		(Iterator((0, 0)) ++ _tiles)
			.map { case (tileX, tileY) => (tileX+x, tileY+y) }
	}
	def rotate = {
		new Block(
			_tiles.map {
				case (x, y) => (y, -x)
			},
			x,
			y,
		)
	}
}
