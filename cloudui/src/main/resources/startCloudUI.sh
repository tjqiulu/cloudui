#!/bin/sh

nohup java -noverify -Xmx512m -jar -Dspring.config.location=./application.yml ./target/cloudui-0.0.1-SNAPSHOT.jar &
echo $! > cloudui.pid
