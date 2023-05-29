IMAGE_NAME = "javarestapi:v1.0.0"

build-image:
	gradle jibDockerBuild --image=$(IMAGE_NAME)