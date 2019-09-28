package model

import block._
import grid._

object Model {
	def empty = {
		new Model(Block.random, Grid.empty)
	}
}

class Model(val block: Block, val grid: Grid) {
	def down = {
		val newBlock = block.down
		if (isBlockValid(newBlock)) {
			new Model(newBlock, grid)
		} else {
			new Model(Block.random, grid.addBlock(block))
		}
	}
	private def isBlockValid(block: Block) = {
		block.tiles.foldLeft (true) {
			(canMove: Boolean, pos: (Int, Int)) => {
				val (x, y) = pos
				canMove && !grid.hasTile(x, y) && x != -1 && x != 8 && y != 20
			}
		}
	}
	def left = {
		val newBlock = block.left
		if (isBlockValid(newBlock)) {
			new Model(newBlock, grid)
		} else {
			this
		}
	}
	def right = {
		val newBlock = block.right
		if (isBlockValid(newBlock)) {
			new Model(newBlock, grid)
		} else {
			this
		}
	}
	def rotate = {
		val newBlock = block.rotate
		if (isBlockValid(newBlock)) {
			new Model(newBlock, grid)
		} else {
			this
		}
	}
	def drop = {
		var newBlock = block
		while (isBlockValid(newBlock.down)) {
			newBlock = newBlock.down
		}
		new Model(newBlock, grid).down
	}
}
