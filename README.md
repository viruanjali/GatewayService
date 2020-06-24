F) Run Zuul API Gateway Docker Container
mvn clean package
docker build --tag=gatewayserver --force-rm=true .
docker tag 779439166f56 amsidhlokhande/gatewayserver
docker push amsidhlokhande/gatewayserver

#docker run -d -e "spring.cloud.config.uri=http://172.31.38.45:8012" -e "spring.rabbitmq.host=172.31.38.45" -p 8011:8011 amsidhlokhande/gatewayserver
docker run -d -e "spring.cloud.config.uri=http://172.31.4.122:8012" -v /home/ec2-user/photoapp-logs:/api-logs -p 8011:8011 amsidhlokhande/gatewayserver

ZUUL API Gateway URI  http://PublicIP:8011/users-ws/actuator/info
                      http://PUBLICIP:8011/actuator/
