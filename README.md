# k-means clustering of image
-----------------------------

Clustering of image by k-means algorithm

The source image is selected when `Main.kt` is starting

To display the image is used `JPanel` and `BufferedImage` in `Image.kt`

Clustered image is saved in root of project

-----------------------------

`IClusterizer.kt` allows you to add others implementations of clustering

`Cluster.kt` and `Pixel.kt` just (nearly) data classes for clustering

`KMeansClusterizer.kt` - my implementation of k-means. Details of implementation are described in comments in class file.


# Results:

`Images layout in README by @alexander-lee description`

| **Source Image**  | **5-means Clustering (with 20 iterations)** |
| :---:  | :---:  |
|![1a](https://user-images.githubusercontent.com/63087212/124401651-352acc80-dd33-11eb-810c-c65ed5ce54ad.jpg)|![clusteredImage(k = 5)](https://user-images.githubusercontent.com/63087212/124401512-930ae480-dd32-11eb-9d76-2030245b2420.jpg)|
| **10-means Clustering (with 20 iterations)**  | **20-means Clustering (with 20 iterations)** |
|![clusteredImage(k = 10)](https://user-images.githubusercontent.com/63087212/124401599-ed0baa00-dd32-11eb-8cb6-7d600542e75b.jpg)|![clusteredImage(k = 20)](https://user-images.githubusercontent.com/63087212/124401625-0876b500-dd33-11eb-9a91-7f5856e2386a.jpg)|
