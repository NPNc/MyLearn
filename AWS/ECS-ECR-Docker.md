https://www.youtube.com/watch?v=aSd1S-4jQ-Q

1. Create IAM
- Create Group
- Add permisson: AmazonEC2ContainerRegistryFullAccess , AmazonECS_FullAccess
- Create User
- Download CSV user
https://582184503127.signin.aws.amazon.com/console
npnc/v$AsZT7^
AKIAYPDHGQ5LWNAJOI7C / 0v9u63HjKWgNBSaawpix6SlNyfZtCcLVui/iSiFJ

ssh -i "npnc-ecs.pem" ec2-user@ec2-122-248-211-209.ap-southeast-1.compute.amazonaws.com


Thá»±c hÃ nh AWS/ECS

1. CÃ i EC2
#!/bin/bash
yum update -y
yum install -y docker
service docker start
chkconfig docker on
curl -L "https://github.com/docker/compose/releases/download/1.11.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose


$ mkdir nginx
$ cd nginx
$ vi Dockerfile
FROM nginx
COPY html /usr/share/nginx/html


$ mkdir html
$ vi html/index.html
<html>
<head><title>Test NPNc</title></head>
<body><h1>Xin chao</h1></body>
</html>


$docker build -t [Image Name]:tag .
	->docker build -t tinhocthatladongian:v1 .

- AWS ECR Login
aws ecr get-login --no-include-email --region ap-southeast-1


- Táº¡o repository lÃªn AWS/ECR(Amazon Elastic Container Registry)
aws ecr create-repository --repository-name [Repository Name] --region eu-west-2
->aws ecr create-repository --repository-name npnc --region ap-southeast-1


- Táº¡o tag version docker image
docker tag [Image Name]:tag [Repository ULR] Ä‘Ã£ táº¡o ra á»Ÿ bÆ°á»›c trÃªn]
->docker tag test:v1 582184503127.dkr.ecr.ap-southeast-1.amazonaws.com/npnc

- Push lÃªn AWS/ECR
docker push [Repository ULR]
->docker push 582184503127.dkr.ecr.ap-southeast-1.amazonaws.com/npnc

- Táº¡o Task trÃªn AWC/ECS
- Táº¡o Clusters trÃªn AWC/ECS







CÃ¡c lá»‡nh xÃ³a Ä‘á»“ng loáº¡t
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi -f $(docker images -a -q)
