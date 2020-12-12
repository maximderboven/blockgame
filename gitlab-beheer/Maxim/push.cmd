@echo off
cd C:\Users\maxim\Desktop\JAVA (FX) blockgame
set /p id="Beschrijving: "
git add .
git commit -m "%id%"
git push -u origin master 