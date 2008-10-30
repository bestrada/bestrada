@del *.pdf

pdflatex thesis.tex
bibtex thesis
pdflatex thesis.tex
pdflatex thesis.tex

@del *.aux
@del *.bbl
@del *.blg
@del *.toc
@del *.log
@del *.lof

pause
cls