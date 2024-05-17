#!/bin/bash

# 현재 시간 가져오기 (YYYY-MM-DD-HH-MM 형식)
current_time=$(date +"%Y-%m-%d-%H-%M")

# Docker 이미지 태그 생성
image_tag="dongbanza-$current_time-container"

# JAR 파일 빌드 (Gradle 사용)
./gradlew clean build -x test

# 빌드 결과 확인
if [ $? -ne 0 ]; then
  echo "Gradle build failed. Exiting."
  exit 1
fi

# Docker 이미지 빌드
docker build -t $image_tag .

# 결과 출력
echo "Docker image built with tag: $image_tag"

# 실행 중인 이전 컨테이너 중지 및 제거
docker stop dongbanza-container 2>/dev/null
docker rm dongbanza-container 2>/dev/null

# Docker 컨테이너 실행
docker run -d -p 8082:8080 --name dongbanza-container $image_tag

# 결과 출력
echo "Docker container running with name: dongbanza-container"
