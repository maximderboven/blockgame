echo Wat is de beschrijving ?
read varname
git add .
git commit -m "$varname"
git push -u origin master 