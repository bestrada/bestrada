del *.dvi
del *.pdf

del *.aux
del *.bbl
del *.blg
del *.toc

texify -c thesis.tex
dvipdfm -p letter thesis.dvi

del *.aux
del *.bbl
del *.blg
del *.toc
del *.log
del *.dvi

pause
