#Imagej demo

Just practicing with the Imagej library.

##Instructions

Package the code:

    mvn package

Run the code:

    java -jar target/imagej-demo-0.0.1-SNAPSHOT.jar \
    [source image location] \
    --resize \
    --crop xXy+WIDTH+HEIGHT \
    --scale scaleFactor \
    [save destination]
