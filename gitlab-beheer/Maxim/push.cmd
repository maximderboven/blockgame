@echo off
cd C:\Users\maxim\OneDrive - Karel de Grote Hogeschool\1ste jaar\Programmeren - JavaFX\JAVA BLOCKGAME
set /p id="Beschrijving: "
git add .
git commit -m "%id%"
git push -u origin master 