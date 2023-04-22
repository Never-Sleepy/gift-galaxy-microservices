RECOMMENDATION_APP_NAME = recommendation-service
RECOMMENDATION_DOCKER_NAME = fishboneapps/recommendation-service
INITIAL_TAG ?= $(shell git rev-parse --short ${GITHUB_SHA})
TAG := $(if $(INITIAL_TAG),$(INITIAL_TAG),$(shell openssl rand -hex 32)-dirty)

docker-build-recommendation:
	docker build -f $(RECOMMENDATION_APP_NAME)/Dockerfile -t $(RECOMMENDATION_DOCKER_NAME) ./$(RECOMMENDATION_APP_NAME)

docker-tag-recommendation: docker-build-recommendation
	docker tag $(RECOMMENDATION_DOCKER_NAME) $(RECOMMENDATION_DOCKER_NAME):latest
	docker tag $(RECOMMENDATION_DOCKER_NAME) $(RECOMMENDATION_DOCKER_NAME):$(TAG)

docker-push-recommendation: docker-tag-recommendation
	docker push $(RECOMMENDATION_DOCKER_NAME):latest
	docker push $(RECOMMENDATION_DOCKER_NAME):$(TAG)

# deploy-recommendation: