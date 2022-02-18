$fpath = "fconfig\programs-list-main.txt" 
$fpathr = "fconfig\ready.txt" 
$redy = "77"
$isfile = Test-Path $fpath 
if($isfile) {
}else{
   Write-host "Файл не существует"
$TempInstallerPath="$Env:USERPROFILE\AppData\Local"
$Desktop="$Env:USERPROFILE\Desktop"
$INCLUDE= "operа.exe","browser.exe","launcher.exe","chrome.exe","chromium.exe","firefox.exe","theworld.exe","dragon.exe"
Get-ChildItem -Path "C:\Program Files\*" -Include $INCLUDE -Recurse  -ErrorAction SilentlyContinue | Out-File $fpath -Encoding "UTF8"
Get-ChildItem -Path "C:\Program Files (x86)\*" -Include $INCLUDE -Recurse -ErrorAction SilentlyContinue | Out-File $fpath -Encoding "UTF8" -Append
Get-ChildItem -Path "C:\Program Data\*" -Include $INCLUDE  -Recurse -ErrorAction SilentlyContinue | Out-File $fpath -Encoding "UTF8" -Append
Get-ChildItem -Path "$TempInstallerPath\*" -Include $INCLUDE  -Recurse -ErrorAction SilentlyContinue | Out-File $fpath -Encoding "UTF8" -Append
Get-ChildItem -Path "$Desktop\*" -Include $INCLUDE  -Recurse -ErrorAction SilentlyContinue | Out-File $fpath -Encoding "UTF8" -Append
$redy | Out-File $fpathr -Encoding "UTF8"
}