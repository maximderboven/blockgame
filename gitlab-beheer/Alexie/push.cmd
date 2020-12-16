@echo off
cd C:\Users\Alexie Chaerle\Desktop\Java project - Block Game
set /p id="Beschrijving: "
git add .
git commit -m "%id%"
git push -u origin master 