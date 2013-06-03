Hibernate Branch für HiberShop
=========

Übungsblätter
-------------

Tag mit dem namen `uebungX` entsprechen dem Stand eines Übungsblattes. Sprich: Der tag `uebung02` repräsentiert die Aufgaben des Übungsblatts 2.

Wie kann ich mir den Stand jetzt angucken?

    git checkout -b branch_name tag_name

So habt ihr einen neuen Branch mit dem Namen `branch_name` der den Stand des tags `tag_name` hat.

Warum ein eigener Branch?
-------------------------

Dieser Branch enthält eine neue Ordnerstruktur die für ein Hibernate Projekt gebraucht wird.
Der Hibernate Branch existiert neben dem JSP Branch und enthält nur die Hibernate Dateien.

Um diesen Branch zu nutzen erstellt ein _Java Project_ in Eclipse und klont
dieses git am besten ein mal und checkt diesen Branch aus.
