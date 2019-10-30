# mif03-chat

tag TP - 1.4
#####question 1.2 Quel est le type de redirection que vous devez employer pour cela ? 
On ne traite pas les données d'un formulaire donc on ne passe pas par un 
doPost, donc on fait un doGet

L'application est fonctionnelle pour cette étape a l'exception d'une 
defaillance pour la deconnexion, quand un utilisateur se déconnecte, le 
prochain utilisateur se vera sur sa page de connexion le dernier billet 
de l'utilisateur precedent.

<<<<<<< HEAD
##### Notre MVC push-based
les Vues se trouvent dans le dossier JSP, le contrôleur est séparé dans plusieurs classe java se trouvent dans le package servlets, et le model se trouve dans le package classes, le contrôleur est chargé d'affecter le model et d'orienter les vues.
=======
##### Notre MVC
Les Vues se trouvent dans le dossier JSP, le contrôleur est séparé 
dans plusieurs classe java se trouvent dans le package servlets, 
et le model se trouve dans le package classes, le contrôleur est 
chargé d'affecter le model et d'orienter les vues.
>>>>>>> 5b6bcdc531a33a889add22f2edc0d921ccbf1022

##### Gestion du cache
Les ressources statiques sont bien mises en caches lors des rafraichissement,
mais on a un souci avec les ressources dynamiques , notre site n'est requeté qu'avec des 
POST et n'appelle pas les GET. 
La gestion des headers HTTP a été effectué sur la servlet Init.
La gestion des cookies est implémenté mais "casse le site" , car en effet le 
doGet n'est pas appelé en temps normal. Il est implémenté sur les commentaires.
