RECOMMENDATION_APP_NAME = recommendation-service
RECOMMENDATION_DOCKER_NAME = fishboneapps/recommendation-service
USER_APP_NAME = user-service
USER_DOCKER_NAME = fishboneapps/user-service
INITIAL_TAG ?= $(shell git rev-parse --short ${GITHUB_SHA})
TAG := $(if $(INITIAL_TAG),$(INITIAL_TAG),$(shell openssl rand -hex 32)-dirty)

# Environment variables: production, development
ENV ?= production

docker-build-recommendation:
	docker build -f $(RECOMMENDATION_APP_NAME)/Dockerfile -t $(RECOMMENDATION_DOCKER_NAME) ./$(RECOMMENDATION_APP_NAME)

docker-tag-recommendation: docker-build-recommendation
	docker tag $(RECOMMENDATION_DOCKER_NAME) $(RECOMMENDATION_DOCKER_NAME):latest
	docker tag $(RECOMMENDATION_DOCKER_NAME) $(RECOMMENDATION_DOCKER_NAME):$(TAG)

docker-push-recommendation: docker-tag-recommendation
	docker push $(RECOMMENDATION_DOCKER_NAME):latest
	docker push $(RECOMMENDATION_DOCKER_NAME):$(TAG)

deploy-recommendation:
	helm upgrade $(RECOMMENDATION_APP_NAME) ./gift-galaxy/charts/recommendation --set global.env=$(ENV),image.repository=$(RECOMMENDATION_DOCKER_NAME),image.tag=$(TAG) --install --atomic

docker-build-user:
	docker build -f $(USER_APP_NAME)/Dockerfile -t $(USER_DOCKER_NAME) ./$(USER_APP_NAME)

docker-tag-user: docker-build-user
	docker tag $(USER_DOCKER_NAME) $(USER_DOCKER_NAME):latest
	docker tag $(USER_DOCKER_NAME) $(USER_DOCKER_NAME):$(TAG)

docker-push-user: docker-tag-user
	docker push $(USER_DOCKER_NAME):latest
	docker push $(USER_DOCKER_NAME):$(TAG)

deploy-user:
	helm upgrade $(USER_APP_NAME) ./gift-galaxy/charts/user --set global.env=$(ENV),image.repository=$(USER_DOCKER_NAME),image.tag=$(TAG) --install --atomic

# Local development
dev:
	mvn clean package
	skaffold dev --port-forward --no-prune=false --cache-artifacts=false