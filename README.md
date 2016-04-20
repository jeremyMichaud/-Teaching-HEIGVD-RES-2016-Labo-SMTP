# Teaching-HEIGVD-RES-2016-Labo-SMTP
## But
Le but de ce laboratoire est d'implémenter un programme qui génère des emails forgés. Un serveur Mock sera utilisé pour tester le programme et ainsi ne pas surcharger ou spammer les serveurs de l'école, ainsi que les victimes.

## Diagramme des classes
![image](./figure/main.jpg)
## Description
Le programme consiste à générer une instance d'un SmtpClient afin que celui-ci communique avec le serveur. Le client utilise des informations sur le serveur qui sont présente dans un fichier de configuration. Ce fichier est directement passé en paramètre du constructeur du ConfigurationManager, qui va dans le même temps ouvrir et lire les fichiers contenant les listes de victimes ainsi que les messages à envoyer.

Afin de créer dynamiquement des listes de victimes, les adresses e-mail des victimes (l'expéditeur ainsi que tous les destinataires) sont lues à partir d'un fichier (une adresse par ligne), et mises dans une liste de personne. Cette liste est mélangée avant chaque envoi, et est divisée en groupes (la taille du groupe est aussi présente dans le fichier de config). La première personne du groupe est désignée comme étant l'expéditeur, et les autres les destinataires. 

Pour ce qui est des messages, la liste les contenant tous est mélangée, et ils sont tirés les uns après les autres (avec une boucle possible). Lors de l'extraction des message du fichier, le sujet du message est présent sur la première ligne, et le corps est constitué par tout le reste. Les messages sont séparés par les caractères "==" (présents seuls sur une ligne).

En suivant le protocol, le client envoie les "commandes" ainsi que l'expéditeur, les destinataires et le message au serveur les uns après les autres et attend la réponse. Le programme affiche les réponses du serveur dans la console.

