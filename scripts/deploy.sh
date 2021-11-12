sudo docker ps -a -q --filter "name=hanghae-board-project2" | grep -q . && docker stop hanghae-board-project2 && docker rm hanghae-board-project2 | true

sudo docker rmi backtony/hanghae-board-project2:1.0

sudo docker pull backtony/hanghae-board-project2:1.0

docker run -d -p 8080:8080 --name hanghae-board-project2 backtony/hanghae-board-project2:1.0

docker rmi -f $(docker images -f "dangling=true" -q) || true