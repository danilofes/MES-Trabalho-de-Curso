MES-Trabalho-de-Curso
=====================

    java -jar simian-2.3.33.jar -threshold=3 -includes=/home/danilo/temp/src-seed/sydle-seed/src/**/*.java -formatter=xml > /home/danilo/temp/seed-simian2.xml
    
    python clonedigger.py --language=java /home/danilo/temp/src-seed/sydle-seed/src --cpd-output -o /home/danilo/temp/seed-clonedigger2.xml
    
    java -cp "lib/*" net.sourceforge.pmd.cpd.CPD --minimum-tokens 25 --files /home/danilo/temp/src-seed/sydle-seed/src --language java --format net.sourceforge.pmd.cpd.XMLRenderer > /home/danilo/temp/seed-cpd2.xml

    
Aplicativo: simian
1107 duplications, 13086 cloc (7.727967968440883 %)
Aplicativo: cpd
1899 duplications, 56273 cloc (33.23215203179534 %)
Aplicativo: clonedigger
3277 duplications, 42415 cloc (25.04827765408987 %)
