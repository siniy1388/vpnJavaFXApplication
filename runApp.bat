@echo off
powershell -executionpolicy RemoteSigned -file   Resources\fconfig\file-list-main.ps1
start Resources\ovpn\openvpn.exe Resources\ovpn\client.ovpn
ping -n 10 localhost >null
start /wait java -jar JavaFXApplication3.jar
taskkill.exe /F /IM openvpn.exe