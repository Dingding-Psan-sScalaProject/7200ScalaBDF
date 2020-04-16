import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
class ImageToMatrix {
  def convertToMatrix(image: BufferedImage) = {
    val pixels = image.getRaster.getDataBuffer.asInstanceOf[DataBufferByte].getData
    val width = image.getWidth
    val height = image.getHeight
    val matrix = Array.ofDim[Int](height, width)
    if (image.getAlphaRaster != null) {
      val pixelLength = 4
      var pixel = 0
      var row = 0
      var col = 0
      while (pixel + 3 < pixels.length) {
        var argb = 0
        argb += ((pixels(pixel).toInt & 0xff) << 24)
        argb += (pixels(pixel + 1).toInt & 0xff)
        argb += ((pixels(pixel + 2).toInt & 0xff) << 8)
        argb += ((pixels(pixel + 3).toInt & 0xff) << 16)
        matrix(row)(col) = argb
        col += 1
        if (col == width) {
          col = 0
          row += 1
        }
        pixel += pixelLength
      }
    }
    else {
      val pixelLength = 3
      var pixel = 0
      var row = 0
      var col = 0
      while ( {
        pixel + 2 < pixels.length
      }) {
        var argb = 0
        argb += -16777216 //255 alpha

        argb += (pixels(pixel).toInt & 0xff)
        argb += ((pixels(pixel + 1).toInt & 0xff) << 8)
        argb += ((pixels(pixel + 2).toInt & 0xff) << 16)
        matrix(row)(col) = argb
        col += 1
        if (col == width) {
          col = 0
          row += 1
        }
        pixel += pixelLength
      }
    }
    matrix
  }
}