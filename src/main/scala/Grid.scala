package grid

import block._

object Grid {
	def empty = new Grid(Set())
}

class Grid(_tiles: Set[(Int, Int)]) {
	def hasTile(x: Int, y: Int): Boolean = {
		_tiles.contains((x, y))
	}
	def tiles = {
		_tiles.iterator
	}
	def addBlock(block: Block) = {
		val gridWithBlock = block.tiles.foldLeft (_tiles) (_ + _)
		var rowCounts = Array.tabulate(20)((i: Int) => 0)
		for (tile <- gridWithBlock) {
			if (tile._2 >= 0) {
				rowCounts(tile._2) += 1
			}
		}
		val gridWithoutRows = rowCounts
			.zipWithIndex
			.filter(_._1 == 8)
			.map(_._2)
			.foldLeft(gridWithBlock) {
				(tiles: Set[(Int, Int)], rowToRemove: Int) => {
					tiles
						.filter { case (_, y) => y != rowToRemove }
						.map { case (x, y) => {
							if (y < rowToRemove) {
								(x, y+1)
							} else {
								(x, y)
							}
						} }
				}
			}
		new Grid(gridWithoutRows)
	}
}
