# Implementation des Gauss Algorithmus
Mit diesem Programm können Gleichungssysteme des Systems A x = B gelöst werden.
Wird das Programm gestartet, kann die Größe n der Matrix gewählt werden. Die Matrix
ist immer quadratisch und damit n x n.
Daraufhin werden die einzelnen Gleichungen angegeben. Die einzelnen Elemente werden
durch Leerzeichen getrennt. Pro Gleichung müssen n + 1 Zahlen eingegeben werden, da
der Ergebnisvektor ein teil der Matrix ist.

## Debugmatrix
Alternativ zur Größe der Matrix kann im ersten Schritt auch eine sogenannte Debugmatrix
ausgewählt werden. Zum Wählen wird die Zahl der Matrix, welche mit einem d gepräfixt wurde,
in die Konsole eingegeben. Folgende Debugmatrizen gibt es aktuell:

d1:

| x1 | x2 | x3 | r |
|----|----|----|---|
| 1  | 2  | 3  | 2 |
| 1  | 1  | 1  | 2 |
| 3  | 3  | 1  | 0 |

d2:

| x1 | x2 | x3 | r   |
|----|----|----|-----|
| 7  | 3  | -5 | -12 |
| -1 | -2 | 4  | 5   |
| -4 | 1  | -3 | 1   |