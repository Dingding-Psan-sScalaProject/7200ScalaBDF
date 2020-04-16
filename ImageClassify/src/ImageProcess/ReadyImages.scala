package ImageProcess
import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO;
class ReadyImages {
  val img = ImageIO.read(new File("myimagefile.jpg"))
    .squarelize
    .resizeImg
    .toGray
}
