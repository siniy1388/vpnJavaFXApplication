@echo off
powershell -executionpolicy RemoteSigned -file   file-list-main.ps1
start openvpn client.ovpn
ping -n 20 localhost >null
start /wait java -jar JavaFXApplication3.jar
taskkill.exe /F /IM openvpn.exe
