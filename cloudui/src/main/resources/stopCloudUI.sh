#!/bin/sh
file="cloudui.pid"
if [ -f "$file" ]
then
 echo $file
 kill -9 `cat cloudui.pid`
 rm -f nohup.out
 echo "kill the cloudui first!"
else
 echo "no cloudui is running!"
fi