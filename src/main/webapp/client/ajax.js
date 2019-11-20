
function hello() {
    console.log("hello world");
}

function refresh() {
    setTimeout("refresh();",5000);
}

function f(action) {
    var billet = {
        titre: "mon billet",
        contenue:" voici mon contenue",
        auteur: "toto",
        commentaire: ["c1","c2","c3"," il fait beau on est en retard"]
    };

    //var output = Mustache.render("titre {{titre}} contenue {{contenue}} auteur {{auteur}} commentaire {{commentaire}}", billet);
    //console.log(output);
    //exemple jquery et mustache pour remplir
    //$('#mu').html(output);

    var Groupe = {
        nom: "Jesus",
        description: "je suis nee a noel",
        proprietaire: "moi",
        membres: ["jean","pierre","juda","paul"],
        billets : [{
            titre: "mon billet 2",
            contenue:" voici mon contenue2",
            auteur: "toto 2",
            commentaire: ["c1","c2","c3"," il fait beau on est en retard"]
        },
            {   titre: "mon billet 3",
                contenue:" voici mon contenue 3",
                auteur: "toto 3",
                commentaire: ["c1","c2","c3"," il fait beau on est en retard"]}]
    };

    //var output2 = Mustache.render("nom {{nom}} description {{description}} membres {{membres}} billets {{billets}}", Groupe);
    //console.log(output2);

    var commentaire = {
        auteur:"vercingetorix",
        texte:"j'ai gagne gergovie"
    };

    var Groupes = {
        groupes:["Arverne","Etrusque"]
    };

    var Billets = {
        billets:["b1","b2"]
    };

    var Commentaires = {
        commentaires: ["yolo1", "yolo2"]
    };
    if(action == "users"){
        var Users = {
            users: ["Asterix", "Obelix","Panoramix"]
        };
        var output = Mustache.render("Users {{users}}", Users);
        console.log(output);
        $('#mu').html(output);
    }
    var Pseudo = {
        pseudo:"le roi de la Gaulle"
    };
}
