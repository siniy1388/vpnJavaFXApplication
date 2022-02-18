#/bin/bash
whereis chromium-browser > fconfig/programs-list-main.txt 
whereis google-chrome >> fconfig/programs-list-main.txt
whereis opera >> fconfig/programs-list-main.txt
whereis yandex >> fconfig/programs-list-main.txt
whereis comodo >> fconfig/programs-list-main.txt
whereis theworld >> fconfig/programs-list-main.txt
whereis tor >> fconfig/programs-list-main.txt
echo "77" > fconfig/ready.txt
