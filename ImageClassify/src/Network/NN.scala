package Network
import org.apache.spark.*


class NN {


  val channels = 1
  val outputNum = 10

  val conf = new NeuralNetConfiguration.Builder()
    .seed(seed)
    .iterations(iterations)
    .regularization(true)
    .l2(0.0005)
    .learningRate(.01)
    .weightInit(WeightInit.XAVIER)
    .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
    .updater(Updater.NESTEROVS)
    .momentum(0.9)
    .list
    .layer(0, new ConvolutionLayer.Builder(5, 5)
      .nIn(channels)
      .stride(1, 1)
      .nOut(20)
      .activation(Activation.IDENTITY)
      .build)
    .layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
      .kernelSize(2, 2)
      .stride(2, 2)
      .build)
    .layer(2, new ConvolutionLayer.Builder(5, 5)
      .stride(1, 1)
      .nOut(50)
      .activation(Activation.IDENTITY)
      .build)
    .layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
      .kernelSize(2, 2)
      .stride(2, 2)
      .build)
    .layer(4, new DenseLayer.Builder()
      .activation(Activation.RELU)
      .nOut(500)
      .build)
    .layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
      .nOut(outputNum)
      .activation(Activation.SOFTMAX).build)
    .setInputType(InputType.convolutionalFlat(28, 28, 1))
    .backprop(true).pretrain(false).build

  val model: MultiLayerNetwork = new MultiLayerNetwork(conf)
  model.init()
  }
}
