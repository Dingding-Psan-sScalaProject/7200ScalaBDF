package ImageProcess

import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO;


//all images to size 50*50 and gray 25*256

class ImageProcess {

  //colored image to gray image
  def toGray(image:BufferedImage):BufferedImage = {

    val width = image.getWidth
    val height = image.getHeight
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        var p = image.getRGB(x, y)
        val a = (p >> 24) & 0xff
        val r = (p >> 16) & 0xff
        val g = (p >> 8) & 0xff
        val b = p & 0xff
        val avg = (r + g + b) / 3
        p = (a << 24) | (avg << 16) | (avg << 8) | avg
        image.setRGB(x, y, p)
      }
    }
    image
  }

  //make our image a square
  def squarelize(image: BufferedImage): BufferedImage = {
    val width = image.getWidth
    val height = image.getHeight
    val l = List(width,height)
    val min = l.min
    image match {
      case x
        if width == height => image
      case x
        if width > height => Scalr.crop(image, (width - height) / 2, 0, min, min)
      case x
        if width < height => Scalr.crop(image, 0, (height - width) / 2, min, min)
    }
  }

  //resize the image
  def resizeImg(image: BufferedImage, width: Int, height: Int):BufferedImage = {
    Scalr.resize(image, Scalr.Method.BALANCED, width, height)
  }

}

object ImageProcess extends App{

  val testImage = ImageIO.read(new File("7205/test.jpg"));
  val squareed = squarelize(testImage)
  val grayed = toGray(squareed)
  val resized = resizeImg(grayed,50,50)
  ImageIO.write((resized,"jpg",new File("ProcessedTestImage")))


  //colored image to gray image
  def toGray(image:BufferedImage):BufferedImage = {

    val width = image.getWidth
    val height = image.getHeight
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        var p = image.getRGB(x, y)
        val a = (p >> 24) & 0xff
        val r = (p >> 16) & 0xff
        val g = (p >> 8) & 0xff
        val b = p & 0xff
        val avg = (r + g + b) / 3
        p = (a << 24) | (avg << 16) | (avg << 8) | avg
        image.setRGB(x, y, p)
      }
    }
    image
  }

  //make our image a square
  def squarelize(image: BufferedImage): BufferedImage = {
    val width = image.getWidth
    val height = image.getHeight
    val l = List(width,height)
    val min = l.min
    image match {
      case x
        if width == height => image
      case x
        if width > height => Scalr.crop(image, (width - height) / 2, 0, min, min)
      case x
        if width < height => Scalr.crop(image, 0, (height - width) / 2, min, min)
    }
  }

  //resize the image
  def resizeImg(image: BufferedImage, width: Int, height: Int):BufferedImage = {
    Scalr.resize(image, Scalr.Method.BALANCED, width, height)
  }

}