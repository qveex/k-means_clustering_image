# k-means clusterinf of image
-----------------------------
-----------------------------
Clustering of image by k-means algorithm

The source image is selected in Main.kt as path to image

Test image is already located in root of project, clustered version of this image also located there (k == 100)
To display the image is used JPanel and BufferedImage in Image.kt

-----------------------------
-----------------------------
IClusterizer allows you to add others implementations of clustering

KMeansClusterizer - my implementation of k-means. Details of implementation are described in comments in class file
Algorithm also saves clustered image in the root of project

Cluster.kt and Pixel.kt just data classes for clustering
