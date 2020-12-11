@echo off
set /p id="Beschrijving: "
read varname
git add .
git commit -m "%id%"
git push -u origin master 