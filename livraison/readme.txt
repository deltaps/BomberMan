Projet réalisé par:
PRONOST Sacha 21901956
SIEPKA Aurélien 21906664
VALLEE Mathieu 21910887
WILLENBUCHER Gurvan 21908377

Pour initialiser notre programme (compilé, crée un javadoc, crée un .jar) il vous suffit
de placer votre bash dans notre dossier de jeu (racine de livraison), puis d’entrer la commande :
ant

Afin de démarrer notre jeu, il vous suffit de lancer le fichier ProjetAnnuel.jar se trouvant
dans le répertoire dist.

Il est aussi possible de lancer le jeu avec notre ant, pour cela placer votre bash dans
notre dossier de jeu, puis entrée la commande :
ant run

Une autre solution pour lancer le programme, est de le faire en ligne de commande basique. Pour cela
placer votre bash dans le dossier principal de notre jeu (où se situe notre fichier build.xml),
ensuite exécuter ces deux lignes de commande :
javac -cp ./lib/PersonnagesJeu.jar -d ./build src/*/*.java
java -cp ./lib/PersonnagesJeu.jar:./build lanceur.Graphique
