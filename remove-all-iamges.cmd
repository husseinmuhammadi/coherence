$images = docker images x-* -q

foreach ($image in $images) {
  docker rmi $image -f
}