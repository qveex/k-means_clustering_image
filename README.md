# k-means clustering of image
-----------------------------

Clustering of image by k-means algorithm

The source image is selected in Main.kt as path to image

Test image is already located in root of project
To display the image is used `JPanel` and `BufferedImage` in `Image.kt`

-----------------------------

`IClusterizer.kt` allows you to add others implementations of clustering

`Cluster.kt` and `Pixel.kt` just data classes for clustering

`KMeansClusterizer.kt` - my implementation of k-means. Details of implementation are described in comments in class file
Algorithm also saves clustered image in the root of project


# Results:

`Images layout in README by @alexander-lee description`

| **Source Image**  | **5-means Clustering** |
| :---:  | :---:  |
|![1a](https://user-images.githubusercontent.com/63087212/124328606-9e270e80-db92-11eb-9766-c1891f965c07.jpg)|![clusteredImage](https://user-images.githubusercontent.com/63087212/124328972-48069b00-db93-11eb-8b61-309b4d9c7a28.jpg)|
| **10-means Clustering**  | **20-means Clustering** |
|![clusteredImage](https://user-images.githubusercontent.com/63087212/124329076-7a17fd00-db93-11eb-8d61-bb60be29151c.jpg)|![clusteredImage](https://user-images.githubusercontent.com/63087212/124329346-f3afeb00-db93-11eb-9444-3be914361ccd.jpg)|
