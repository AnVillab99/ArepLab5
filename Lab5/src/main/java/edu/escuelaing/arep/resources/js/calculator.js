
calculator = (function () {
    var datos;

    return {

        ponerDatos: function (datos2) {
            var dat = JSON.parse(datos2);

        },
        obtenerDatos: function () {     
            console.log("d");
            window.location = window.location.href+"reto2/usuarios";
        }
    };  
})();
calculadora = (function () {
    return {      
        getResultadosR: function (url,datas, callback) {
            console.log("pidiendo ajax");
        $.ajax({
            url: url,
            data: datas,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/javascript',
            success: function(data){
                console.log("data :"+data);
                callback(data);
            },
            error: function(data){
                console.log("error: "+JSON.stringify(datas));
            },
        });
        }
    };

})();