alias mvn="docker run \
  -it \
  --rm \
  --link artifactory:artifactory \
  --name my-maven-project \
  -v "$PWD":/root/.m2 \
  -v "$PWD":/usr/src/mymaven \
  -w /usr/src/mymaven \
  maven:3.3-jdk-8 \
  mvn"

